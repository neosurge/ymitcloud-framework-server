package com.ymitcloud.module.product.controller.admin.property.vo.value;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品属性值分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPropertyValuePageReqVO extends PageParam {

    /**
     * 属性项的编号
     */
    private String propertyId;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */

    private Integer status;

}
