package com.ymit.module.system.controller.admin.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.operatelog.core.annotations.OperateLog;
import com.ymit.framework.security.config.SecurityProperties;
import com.ymit.framework.security.core.util.SecurityFrameworkUtils;
import com.ymit.module.system.controller.admin.auth.vo.*;
import com.ymit.module.system.convert.auth.AuthConvert;
import com.ymit.module.system.dal.dataobject.permission.MenuDO;
import com.ymit.module.system.dal.dataobject.permission.RoleDO;
import com.ymit.module.system.dal.dataobject.user.AdminUserDO;
import com.ymit.module.system.enums.logger.LoginLogTypeEnum;
import com.ymit.module.system.service.auth.AdminAuthService;
import com.ymit.module.system.service.permission.MenuService;
import com.ymit.module.system.service.permission.PermissionService;
import com.ymit.module.system.service.permission.RoleService;
import com.ymit.module.system.service.social.SocialClientService;
import com.ymit.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.ymit.framework.common.data.CommonResult.success;
import static com.ymit.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymit.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 管理后台 - 认证
 */
@RestController
@RequestMapping("/system/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AdminAuthService authService;
    @Resource
    private AdminUserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private SocialClientService socialClientService;

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 使用账号密码登录
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/login")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return success(this.authService.login(reqVO));
    }

    /**
     * 应用中台授权登录
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/login-by-app")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> loginByApp(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(this.authService.loginByApp(reqVO));
    }

    /**
     * 登出系统
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, this.securityProperties.getTokenHeader(),
                this.securityProperties.getTokenParameter());
        if (StrUtil.isNotBlank(token)) {
            this.authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
        }
        return success(true);
    }

    /**
     * 刷新令牌
     *
     * @param refreshToken 刷新令牌
     * @return
     */
    @PostMapping("/refresh-token")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(this.authService.refreshToken(refreshToken));
    }

    /**
     * 获取登录用户的权限信息
     *
     * @return
     */
    @GetMapping("/get-permission-info")
    public CommonResult<AuthPermissionInfoRespVO> getPermissionInfo() {
        // 1.1 获得用户信息
        AdminUserDO user = this.userService.getUser(getLoginUserId());
        if (user == null) {
            return null;
        }

        // 1.2 获得角色列表
        Set<Long> roleIds = this.permissionService.getUserRoleIdListByUserId(getLoginUserId());
        if (CollUtil.isEmpty(roleIds)) {
            return success(AuthConvert.convert(user, Collections.emptyList(), Collections.emptyList()));
        }
        List<RoleDO> roles = this.roleService.getRoleList(roleIds);
        roles.removeIf(role -> !CommonStatusEnum.ENABLE.getStatus().equals(role.getStatus())); // 移除禁用的角色

        // 1.3 获得菜单列表
        Set<Long> menuIds = this.permissionService.getRoleMenuListByRoleId(convertSet(roles, RoleDO::getId));
        List<MenuDO> menuList = this.menuService.getMenuList(menuIds);
        menuList.removeIf(menu -> !CommonStatusEnum.ENABLE.getStatus().equals(menu.getStatus())); // 移除禁用的菜单

        // 2. 拼接结果返回
        return success(AuthConvert.convert(user, roles, menuList));
    }

    // ========== 短信登录相关 ==========

    /**
     * 使用短信验证码登录
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/sms-login")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> smsLogin(@RequestBody @Valid AuthSmsLoginReqVO reqVO) {
        return success(this.authService.smsLogin(reqVO));
    }

    /**
     * 发送手机验证码
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/send-sms-code")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
        this.authService.sendSmsCode(reqVO);
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
    @PermitAll
    public CommonResult<String> socialLogin(@RequestParam("type") Integer type,
                                            @RequestParam("redirectUri") String redirectUri) {
        return success(this.socialClientService.getAuthorizeUrl(type, UserTypeEnum.ADMIN.getValue(), redirectUri));
    }

    /**
     * 社交快捷登录，使用 code 授权码,适合未登录的用户，但是社交账号已绑定用户
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/social-login")
    @PermitAll
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> socialQuickLogin(@RequestBody @Valid AuthSocialLoginReqVO reqVO) {
        return success(this.authService.socialLogin(reqVO));
    }

}
