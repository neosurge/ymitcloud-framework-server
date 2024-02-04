package com.ymitcloud.module.product.controller.app.property.vo.value;


import lombok.Data;

/**
 * 用户 App - 商品属性值的明细 Response VO
 */
@Data
public class AppProductPropertyValueDetailRespVO {
    /**
     * 属性的编号
     */
    private Long propertyId;
    /**
     * 属性的名称
     */
    private String propertyName;
    /**
     * 属性值的编号
     */
    private Long valueId;
    /**
     * 属性值的名称
     */

    private String valueName;

}
