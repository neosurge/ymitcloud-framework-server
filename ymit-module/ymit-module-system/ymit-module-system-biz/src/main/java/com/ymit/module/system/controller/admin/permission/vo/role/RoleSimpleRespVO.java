package com.ymit.module.system.controller.admin.permission.vo.role;


import lombok.Data;

/**
 * 管理后台 - 角色精简信息 Response VO
 */
@Data
public class RoleSimpleRespVO {
    /**
     * 角色编号
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;

}
