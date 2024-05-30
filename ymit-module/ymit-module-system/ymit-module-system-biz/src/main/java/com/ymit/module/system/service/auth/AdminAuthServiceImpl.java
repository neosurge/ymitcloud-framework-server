package com.ymit.module.system.service.auth;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.annotations.VisibleForTesting;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.common.exception.util.ServiceExceptionUtil;
import com.ymit.framework.common.util.monitor.TracerUtils;
import com.ymit.framework.common.util.servlet.ServletUtils;
import com.ymit.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.ymit.module.system.api.sms.SmsCodeApi;
import com.ymit.module.system.api.social.dto.SocialUserBindReqDTO;
import com.ymit.module.system.api.social.dto.SocialUserRespDTO;
import com.ymit.module.system.controller.admin.auth.vo.*;
import com.ymit.module.system.convert.auth.AuthConvert;
import com.ymit.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;
import com.ymit.module.system.enums.ErrorCodeConstants;
import com.ymit.module.system.enums.logger.LoginLogTypeEnum;
import com.ymit.module.system.enums.logger.LoginResultEnum;
import com.ymit.module.system.enums.oauth2.OAuth2ClientConstants;
import com.ymit.module.system.enums.sms.SmsSceneEnum;
import com.ymit.module.system.service.logger.LoginLogService;
import com.ymit.module.system.service.member.MemberService;
import com.ymit.module.system.service.oauth2.OAuth2TokenService;
import com.ymit.module.system.service.social.SocialUserService;
import com.ymit.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.ymit.framework.common.util.servlet.ServletUtils.getClientIP;

/**
 * Auth Service 实现类
 */
@Service
@Slf4j
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private AdminUserService userService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private SocialUserService socialUserService;
    @Resource
    private MemberService memberService;
    @Resource
    private Validator validator;
    //    @Resource
//    private CaptchaService captchaService;
    @Resource
    private SmsCodeApi smsCodeApi;

    @Resource
    private RestTemplate restTemplate;
    /**
     * 验证码的开关，默认为 true
     */
    @Value("${ymit.captcha.enable:true}")
    private Boolean captchaEnable;

    @Override
    public AdminUserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserDO user = this.userService.getUserByUsername(username);
        if (user == null) {
            this.createLoginLog(null, username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!this.userService.isPasswordMatch(password, user.getPassword())) {
            this.createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (CommonStatusEnum.isDisable(user.getStatus())) {
            this.createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 校验验证码
        this.validateCaptcha(reqVO);

        // 使用账号密码，进行登录
        AdminUserDO user = this.authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 如果 socialType 非空，说明需要绑定社交用户
        if (reqVO.getSocialType() != null) {
            this.socialUserService.bindSocialUser(new SocialUserBindReqDTO(user.getId(), this.getUserType().getValue(),
                    reqVO.getSocialType(), reqVO.getSocialCode(), reqVO.getSocialState()));
        }
        // 创建 Token 令牌，记录登录日志
        return this.createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO) {
        // 登录场景，验证是否存在
        if (this.userService.getUserByMobile(reqVO.getMobile()) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AUTH_MOBILE_NOT_EXISTS);
        }
        // 发送验证码
        this.smsCodeApi.sendSmsCode(AuthConvert.convert(reqVO).setCreateIp(getClientIP()));
    }

    @Override
    public AuthLoginRespVO smsLogin(AuthSmsLoginReqVO reqVO) {
        // 校验验证码
        this.smsCodeApi.useSmsCode(AuthConvert.convert(reqVO, SmsSceneEnum.ADMIN_MEMBER_LOGIN.getScene(), getClientIP()));

        // 获得用户信息
        AdminUserDO user = this.userService.getUserByMobile(reqVO.getMobile());
        if (user == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        return this.createTokenAfterLoginSuccess(user.getId(), reqVO.getMobile(), LoginLogTypeEnum.LOGIN_MOBILE);
    }

    private void createLoginLog(Long userId, String username,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(this.getUserType().getValue());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        this.loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            this.userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    @Override
    public AuthLoginRespVO socialLogin(AuthSocialLoginReqVO reqVO) {
        // 使用 code 授权码，进行登录。然后，获得到绑定的用户编号
        SocialUserRespDTO socialUser = this.socialUserService.getSocialUser(UserTypeEnum.ADMIN.getValue(), reqVO.getType(),
                reqVO.getCode(), reqVO.getState());
        if (socialUser == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AUTH_THIRD_LOGIN_NOT_BIND);
        }

        // 获得用户
        AdminUserDO user = this.userService.getUser(socialUser.getUserId());
        if (user == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        return this.createTokenAfterLoginSuccess(user.getId(), user.getUsername(), LoginLogTypeEnum.LOGIN_SOCIAL);
    }

    @VisibleForTesting
    void validateCaptcha(AuthLoginReqVO reqVO) {
        // 如果验证码关闭，则不进行校验
        if (!this.captchaEnable) {
            return;
        }
        return;
        //TODO
//        // 校验验证码
//        ValidationUtils.validate(this.validator, reqVO, AuthLoginReqVO.CodeEnableGroup.class);
//        CaptchaVO captchaVO = new CaptchaVO();
//        captchaVO.setCaptchaVerification(reqVO.getCaptchaVerification());
//        ResponseModel response = this.captchaService.verification(captchaVO);
//        // 验证不通过
//        if (!response.isSuccess()) {
//            // 创建登录失败日志（验证码不正确)
//            this.createLoginLog(null, reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
//            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AUTH_LOGIN_CAPTCHA_CODE_ERROR, response.getRepMsg());
//        }
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        this.createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = this.oauth2TokenService.createAccessToken(userId, this.getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        return AuthConvert.convert(accessTokenDO);
    }

    @Override
    public AuthLoginRespVO refreshToken(String refreshToken) {
        OAuth2AccessTokenDO accessTokenDO = this.oauth2TokenService.refreshAccessToken(refreshToken, OAuth2ClientConstants.CLIENT_ID_DEFAULT);
        return AuthConvert.convert(accessTokenDO);
    }

    @Override
    public AuthLoginRespVO loginByApp(AppAuthLoginReqVO reqVO) {
        return null;
    }

    @Override
    public void logout(String token, Integer logType) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = this.oauth2TokenService.removeAccessToken(token);
        if (accessTokenDO == null) {
            return;
        }
        // 删除成功，则记录登出日志
        this.createLogoutLog(accessTokenDO.getUserId(), accessTokenDO.getUserType(), logType);
    }

    private void createLogoutLog(Long userId, Integer userType, Integer logType) {
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType);
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userType);
        if (ObjectUtil.equal(this.getUserType().getValue(), userType)) {
            reqDTO.setUsername(this.getUsername(userId));
        } else {
            reqDTO.setUsername(this.memberService.getMemberUserMobile(userId));
        }
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        this.loginLogService.createLoginLog(reqDTO);
    }

    private String getUsername(Long userId) {
        if (userId == null) {
            return null;
        }
        AdminUserDO user = this.userService.getUser(userId);
        return user != null ? user.getUsername() : null;
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

}
