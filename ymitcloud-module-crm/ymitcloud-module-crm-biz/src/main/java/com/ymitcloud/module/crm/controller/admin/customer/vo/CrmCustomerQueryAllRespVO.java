package com.ymitcloud.module.crm.controller.admin.customer.vo;


import lombok.Data;

/**
 * 管理后台 - CRM 全部客户 Response VO
 */
// TODO 云码：这块要统一下；
@Data
public class CrmCustomerQueryAllRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 客户名称
     */

    private String name;

}
