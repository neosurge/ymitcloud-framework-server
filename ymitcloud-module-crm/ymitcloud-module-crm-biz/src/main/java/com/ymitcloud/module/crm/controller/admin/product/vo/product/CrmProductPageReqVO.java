package com.ymitcloud.module.crm.controller.admin.product.vo.product;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 产品分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmProductPageReqVO extends PageParam {

    /**
     * 产品名称
     */
    private String name;
    /**
     * 状态
     */

    private Integer status;

}
