package com.ymitcloud.module.product.controller.admin.property.vo.property;


import java.util.List;

import lombok.Data;

/**
 * 管理后台 - 商品属性项 + 属性值 Response VO
 */
@Data
public class ProductPropertyAndValueRespVO {
    /**
     * 属性项的编号
     */
    private Long id;
    /**
     * 属性项的名称
     */

    private String name;

    /**
     * 属性值的集合
     */
    private List<Value> values;


    /**
     * 管理后台 - 属性值的简单 Response VO
     */
    @Data
    public static class Value {
        /**
         * 属性值的编号
         */
        private Long id;
        /**
         * 属性值的名称
         */

        private String name;

    }

}
