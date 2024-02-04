package com.ymitcloud.module.crm.controller.admin.permission.vo;


import java.util.List;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.crm.enums.common.CrmBizTypeEnum;
import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - CRM 数据权限更新 Request VO
 */
@Data
public class CrmPermissionUpdateReqVO {
    /**
     * 数据权限编号列表
     */
    @NotNull(message = "数据权限编号列表不能为空")
    private List<Long> ids;
    /**
     * Crm 类型
     */
    @InEnum(CrmBizTypeEnum.class)
    @NotNull(message = "Crm 类型不能为空")
    private Integer bizType;
    /**
     * Crm 类型数据编号
     */
    @NotNull(message = "Crm 类型数据编号不能为空")
    private Long bizId;
    /**
     * 权限级别
     */

    @InEnum(CrmPermissionLevelEnum.class)
    @NotNull(message = "权限级别不能为空")
    private Integer level;

}
