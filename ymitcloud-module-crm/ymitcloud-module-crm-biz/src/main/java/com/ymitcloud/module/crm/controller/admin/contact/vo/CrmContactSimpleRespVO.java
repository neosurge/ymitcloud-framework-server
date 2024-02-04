package com.ymitcloud.module.crm.controller.admin.contact.vo;


import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - CRM 联系人的精简 Response VO
 */
@Data
@ToString(callSuper = true)
public class CrmContactSimpleRespVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */

    private String name;

}
