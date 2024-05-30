package com.ymit.module.system.controller.admin.permission;

import cn.hutool.core.collection.CollUtil;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.module.system.controller.admin.permission.vo.permission.PermissionAssignRoleDataScopeReqVO;
import com.ymit.module.system.controller.admin.permission.vo.permission.PermissionAssignRoleMenuReqVO;
import com.ymit.module.system.controller.admin.permission.vo.permission.PermissionAssignUserRoleReqVO;
import com.ymit.module.system.service.permission.PermissionService;
import com.ymit.module.system.service.tenant.TenantService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - 权限
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;
    @Resource
    private TenantService tenantService;

    /**
     * 获得角色拥有的菜单编号
     *
     * @param roleId 角色编号
     * @return
     */
    @GetMapping("/list-role-menus")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-role-menu')")
    public CommonResult<Set<Long>> getRoleMenuList(Long roleId) {
        return success(this.permissionService.getRoleMenuListByRoleId(roleId));
    }

    /**
     * 赋予角色菜单
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/assign-role-menu")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-role-menu')")
    public CommonResult<Boolean> assignRoleMenu(@Validated @RequestBody PermissionAssignRoleMenuReqVO reqVO) {
        // 开启多租户的情况下，需要过滤掉未开通的菜单
        this.tenantService.handleTenantMenu(
                menuIds -> reqVO.getMenuIds().removeIf(menuId -> !CollUtil.contains(menuIds, menuId)));

        // 执行菜单的分配
        this.permissionService.assignRoleMenu(reqVO.getRoleId(), reqVO.getMenuIds());
        return success(true);
    }

    /**
     * 赋予角色数据权限
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/assign-role-data-scope")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-role-data-scope')")
    public CommonResult<Boolean> assignRoleDataScope(@Valid @RequestBody PermissionAssignRoleDataScopeReqVO reqVO) {
        this.permissionService.assignRoleDataScope(reqVO.getRoleId(), reqVO.getDataScope(), reqVO.getDataScopeDeptIds());
        return success(true);
    }

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param userId 用户编号
     * @return
     */
    @GetMapping("/list-user-roles")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-user-role')")
    public CommonResult<Set<Long>> listAdminRoles(@RequestParam("userId") Long userId) {
        return success(this.permissionService.getUserRoleIdListByUserId(userId));
    }

    /**
     * 赋予用户角色
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/assign-user-role")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-user-role')")
    public CommonResult<Boolean> assignUserRole(@Validated @RequestBody PermissionAssignUserRoleReqVO reqVO) {
        this.permissionService.assignUserRole(reqVO.getUserId(), reqVO.getRoleIds());
        return success(true);
    }

}
