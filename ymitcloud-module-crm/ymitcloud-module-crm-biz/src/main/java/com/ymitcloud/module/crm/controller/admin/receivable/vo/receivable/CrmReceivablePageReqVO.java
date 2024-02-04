package com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 回款分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmReceivablePageReqVO extends PageParam {


    /**
     * 回款编号
     */
    private String no;

    /**
     * 回款计划编号
     */
    private Long planId;
    /**
     * 客户编号
     */

    private Long customerId;

}
