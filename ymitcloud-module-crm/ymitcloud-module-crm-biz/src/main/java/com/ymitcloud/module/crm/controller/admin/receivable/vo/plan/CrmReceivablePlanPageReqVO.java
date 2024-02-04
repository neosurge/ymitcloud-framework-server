package com.ymitcloud.module.crm.controller.admin.receivable.vo.plan;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 回款计划分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmReceivablePlanPageReqVO extends PageParam {


    /**
     * 客户编号
     */
    private Long customerId;

    // TODO @云码：这个搜的应该是合同编号 no
    /**
     * 合同名称
     */

    private Long contractId;

}
