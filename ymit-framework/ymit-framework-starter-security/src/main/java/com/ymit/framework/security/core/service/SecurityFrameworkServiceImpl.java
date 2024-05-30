package com.ymit.framework.security.core.service;

import cn.hutool.core.collection.CollUtil;
import com.ymit.framework.common.data.LoginUser;
import com.ymit.framework.security.core.util.SecurityFrameworkUtils;
import com.ymit.module.system.api.permission.PermissionApi;

import java.util.Arrays;


/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 * @author Y.S
 * @date 2024.05.16
 */
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {
    private final PermissionApi permissionApi;

    public SecurityFrameworkServiceImpl(PermissionApi permissionApi) {
        this.permissionApi = permissionApi;
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.hasAnyPermissions(permission);
    }

    @Override
    public boolean hasAnyPermissions(String... permissions) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        return this.permissionApi.hasAnyPermissions(loginUserId, permissions);
    }

    @Override
    public boolean hasRole(String role) {
        return this.hasAnyRoles(role);
    }

    @Override
    public boolean hasAnyRoles(String... roles) {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        return this.permissionApi.hasAnyRoles(loginUserId, roles);
    }

    @Override
    public boolean hasScope(String scope) {
        return this.hasAnyScopes(scope);
    }

    @Override
    public boolean hasAnyScopes(String... scope) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return false;
        }
        return CollUtil.containsAny(user.getScopes(), Arrays.asList(scope));
    }
}
