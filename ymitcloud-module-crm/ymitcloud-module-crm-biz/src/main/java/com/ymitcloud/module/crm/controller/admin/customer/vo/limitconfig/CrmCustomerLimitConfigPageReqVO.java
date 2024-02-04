package com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 客户限制配置分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerLimitConfigPageReqVO extends PageParam {

    /**
     * 规则类型
     */

    private Integer type;

}
