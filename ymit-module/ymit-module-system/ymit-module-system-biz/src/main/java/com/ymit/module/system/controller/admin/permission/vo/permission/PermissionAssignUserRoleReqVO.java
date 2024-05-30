package com.ymit.module.system.controller.admin.permission.vo.permission;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

/**
 * 管理后台 - 赋予用户角色 Request VO
 */
@Data
public class PermissionAssignUserRoleReqVO {
    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    /**
     * 角色编号列表
     */
    private Set<Long> roleIds = Collections.emptySet(); // 兜底

}
