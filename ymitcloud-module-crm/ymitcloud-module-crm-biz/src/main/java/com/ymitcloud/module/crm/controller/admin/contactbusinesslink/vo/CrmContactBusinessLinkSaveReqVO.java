package com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - CRM 联系人商机关联新增/修改 Request VO
 */
@Data
public class CrmContactBusinessLinkSaveReqVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 联系人编号
     */
    @NotNull(message = "联系人不能为空")
    private Long contactId;
    /**
     * 商机编号
     */
    @NotNull(message = "商机不能为空")

    private Long businessId;

}