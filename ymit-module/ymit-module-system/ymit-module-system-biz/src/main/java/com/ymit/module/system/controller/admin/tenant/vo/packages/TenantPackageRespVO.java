package com.ymit.module.system.controller.admin.tenant.vo.packages;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 管理后台 - 租户套餐 Response VO
 */
@Data
public class TenantPackageRespVO {
    /**
     * 套餐编号
     */
    private Long id;
    /**
     * 套餐名
     */
    private String name;
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联的菜单编号
     */
    private Set<Long> menuIds;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
