package com.ymit.module.system.controller.admin.tenant.vo.packages;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 租户套餐精简 Response VO
 */
@Data
public class TenantPackageSimpleRespVO {
    /**
     * 套餐编号
     */
    @NotNull(message = "套餐编号不能为空")
    private Long id;
    /**
     * 套餐名
     */
    @NotNull(message = "套餐名不能为空")
    private String name;

}
