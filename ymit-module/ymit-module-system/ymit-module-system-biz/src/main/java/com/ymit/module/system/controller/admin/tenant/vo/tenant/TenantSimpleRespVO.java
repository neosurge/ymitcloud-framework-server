package com.ymit.module.system.controller.admin.tenant.vo.tenant;


import lombok.Data;

/**
 * 管理后台 - 租户精简 Response VO
 */
@Data
public class TenantSimpleRespVO {

    /**
     * 租户编号
     */
    private Long id;

    /**
     * 租户名
     */
    private String name;

}
