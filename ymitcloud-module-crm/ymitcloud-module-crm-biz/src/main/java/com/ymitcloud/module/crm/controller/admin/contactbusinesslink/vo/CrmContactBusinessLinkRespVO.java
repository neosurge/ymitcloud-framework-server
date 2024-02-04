package com.ymitcloud.module.crm.controller.admin.contactbusinesslink.vo;


import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 管理后台 - CRM 联系人商机关联 Response VO
 */
@Data
public class CrmContactBusinessLinkRespVO {

    /**
     * 主键
     */
    @ExcelProperty("主键")
    private Long id;
    /**
     * 联系人编号
     */
    private Long contactId;
    /**
     * 商机编号
     */
    private Long businessId;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}