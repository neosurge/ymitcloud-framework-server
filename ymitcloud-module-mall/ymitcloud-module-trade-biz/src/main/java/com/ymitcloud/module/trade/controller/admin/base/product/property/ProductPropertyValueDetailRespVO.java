package com.ymitcloud.module.trade.controller.admin.base.product.property;



import lombok.Data;

/** 管理后台 - 商品属性值的明细 */
@Data
public class ProductPropertyValueDetailRespVO {

    /** 属性的编号*/
    private Long propertyId;

    /** 属性的名称*/
    private String propertyName;

    /** 属性值的编号*/
    private Long valueId;

    /** 属性值的名称*/

    private String valueName;

}
