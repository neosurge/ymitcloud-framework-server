package com.ymitcloud.module.product.controller.admin.property.vo.property;


import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 属性项 List Request VO
 */
@Data
@ToString(callSuper = true)
public class ProductPropertyListReqVO {
    /**
     * 属性名称
     */

    private String name;

}
