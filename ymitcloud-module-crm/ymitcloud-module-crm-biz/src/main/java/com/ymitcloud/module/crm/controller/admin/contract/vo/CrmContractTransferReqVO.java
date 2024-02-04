package com.ymitcloud.module.crm.controller.admin.contract.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - CRM 合同转移 Request VO
 */
@Data
public class CrmContractTransferReqVO {
    /**
     * 合同编号
     */
    @NotNull(message = "联系人编号不能为空")
    private Long id;
    /**
     * 新负责人的用户编号
     */
    @NotNull(message = "新负责人的用户编号不能为空")
    private Long newOwnerUserId;
    /**
     * 老负责人加入团队后的权限级别
     */

    @InEnum(value = CrmPermissionLevelEnum.class)
    private Integer oldOwnerPermissionLevel;

}
