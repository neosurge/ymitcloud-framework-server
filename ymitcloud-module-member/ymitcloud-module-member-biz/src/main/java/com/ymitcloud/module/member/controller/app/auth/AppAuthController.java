package com.ymitcloud.module.member.controller.app.auth;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.framework.security.config.SecurityProperties;
import com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils;

import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthLoginReqVO;
import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthLoginRespVO;
import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthSmsLoginReqVO;
import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthSmsSendReqVO;
import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthSmsValidateReqVO;
import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthSocialLoginReqVO;
import com.ymitcloud.module.member.controller.app.auth.vo.AppAuthWeixinMiniAppLoginReqVO;
import com.ymitcloud.module.member.convert.auth.AuthConvert;
import com.ymitcloud.module.member.service.auth.MemberAuthService;
import com.ymitcloud.module.system.api.social.SocialClientApi;
import com.ymitcloud.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;

import cn.hutool.core.util.StrUtil;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 认证
 */

@RestController
@RequestMapping("/member/auth")
@Validated
@Slf4j
public class AppAuthController {

    @Resource
    private MemberAuthService authService;

    @Resource
    private SocialClientApi socialClientApi;

    @Resource
    private SecurityProperties securityProperties;


    /**
     * 使用手机 + 密码登录
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/login")

    public CommonResult<AppAuthLoginRespVO> login(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }


    /**
     * 登出系统
     * 
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @PermitAll
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader(),
                securityProperties.getTokenParameter());

        if (StrUtil.isNotBlank(token)) {
            authService.logout(token);
        }
        return success(true);
    }


    /**
     * 刷新令牌
     * 
     * @param refreshToken refreshToken
     * @return
     */
    @PostMapping("/refresh-token")

    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AppAuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }

    // ========== 短信登录相关 ==========

    /**
     * 使用手机 + 验证码登录
     * 
     * @param reqVO
     * @param terminal
     * @return
     */
    @PostMapping("/sms-login")
    public CommonResult<AppAuthLoginRespVO> smsLogin(@RequestBody @Valid AppAuthSmsLoginReqVO reqVO,
            @RequestHeader Integer terminal) {
        return success(authService.smsLogin(reqVO, terminal));
    }

    /**
     * 发送手机验证码
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/send-sms-code")

    public CommonResult<Boolean> sendSmsCode(@RequestBody @Valid AppAuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 校验手机验证码
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/validate-sms-code")

    public CommonResult<Boolean> validateSmsCode(@RequestBody @Valid AppAuthSmsValidateReqVO reqVO) {
        authService.validateSmsCode(getLoginUserId(), reqVO);
        return success(true);
    }

    // ========== 社交登录相关 ==========

    /**
     * 社交授权的跳转
     * 
     * @param type        社交类型
     * @param redirectUri 回调路径
     * @return
     */
    @GetMapping("/social-auth-redirect")
    public CommonResult<String> socialAuthRedirect(@RequestParam("type") Integer type,
            @RequestParam("redirectUri") String redirectUri) {
        return CommonResult.success(authService.getSocialAuthorizeUrl(type, redirectUri));
    }

    /**
     * 社交快捷登录，使用 code 授权码
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/social-login")

    public CommonResult<AppAuthLoginRespVO> socialLogin(@RequestBody @Valid AppAuthSocialLoginReqVO reqVO) {
        return success(authService.socialLogin(reqVO));
    }


    /**
     * 微信小程序的一键登录
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/weixin-mini-app-login")
    public CommonResult<AppAuthLoginRespVO> weixinMiniAppLogin(
            @RequestBody @Valid AppAuthWeixinMiniAppLoginReqVO reqVO) {
        return success(authService.weixinMiniAppLogin(reqVO));
    }

    /**
     * 创建微信 JS SDK 初始化所需的签名
     * 
     * @param url
     * @return
     */
    @PostMapping("/create-weixin-jsapi-signature")
    public CommonResult<SocialWxJsapiSignatureRespDTO> createWeixinMpJsapiSignature(@RequestParam("url") String url) {
        SocialWxJsapiSignatureRespDTO signature = socialClientApi
                .createWxMpJsapiSignature(UserTypeEnum.MEMBER.getValue(), url);

        return success(AuthConvert.INSTANCE.convert(signature));
    }

}
