package com.ymitcloud.module.crm.controller.admin.contact.vo;

import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - CRM 联系人转移 Request VO
 */
@Data
public class CrmContactTransferReqVO {
    /**
     * 联系人编号
     */

    @NotNull(message = "联系人编号不能为空")
    private Long id;

    /**
     * 新负责人的用户编号
     */


    @NotNull(message = "新负责人的用户编号不能为空")
    private Long newOwnerUserId;

    /**
     * 老负责人加入团队后的权限级别。如果 null 说明移除
     *
     * 关联 {@link CrmPermissionLevelEnum}
     */


    private Integer oldOwnerPermissionLevel;

}
