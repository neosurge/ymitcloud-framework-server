package com.ymitcloud.module.crm.controller.admin.business.vo.business;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商机分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmBusinessPageReqVO extends PageParam {

    /**
     * 商机名称
     */
    private String name;
    /**
     * 客户编号
     */

    private Long customerId;

}
