package com.ymitcloud.module.crm.controller.admin.contract.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 合同分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmContractPageReqVO extends PageParam {

    /**
     * 合同编号
     */
    private String no;
    /**
     * 合同名称
     */
    private String name;
    /**
     * 客户编号
     */
    private Long customerId;
    /**
     * 商机编号
     */

    private Long businessId;

}
