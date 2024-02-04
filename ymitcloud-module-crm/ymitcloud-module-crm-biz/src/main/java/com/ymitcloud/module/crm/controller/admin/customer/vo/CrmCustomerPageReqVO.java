package com.ymitcloud.module.crm.controller.admin.customer.vo;

import com.ymitcloud.module.crm.framework.vo.CrmBasePageReqVO;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 客户分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerPageReqVO extends CrmBasePageReqVO {

    /**
     * 客户名称
     */
    private String name;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 所属行业
     */
    private Integer industryId;
    /**
     * 客户等级
     */
    private Integer level;
    /**
     * 客户来源
     */

    private Integer source;

}
