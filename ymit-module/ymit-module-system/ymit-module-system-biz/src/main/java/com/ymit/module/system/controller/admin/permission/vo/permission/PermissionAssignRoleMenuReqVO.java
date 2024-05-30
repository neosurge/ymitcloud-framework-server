package com.ymit.module.system.controller.admin.permission.vo.permission;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

/**
 * 管理后台 - 赋予角色菜单 Request VO
 */
@Data
public class PermissionAssignRoleMenuReqVO {
    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Long roleId;
    /**
     * 菜单编号列表
     */
    private Set<Long> menuIds = Collections.emptySet(); // 兜底

}
