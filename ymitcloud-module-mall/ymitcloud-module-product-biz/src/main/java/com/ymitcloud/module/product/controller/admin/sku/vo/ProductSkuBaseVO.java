package com.ymitcloud.module.product.controller.admin.sku.vo;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 商品 SKU Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductSkuBaseVO {
    /**
     * 商品 SKU 名字
     */
    @NotEmpty(message = "商品 SKU 名字不能为空")
    private String name;
    /**
     * 销售价格，单位：分
     */
    @NotNull(message = "销售价格，单位：分不能为空")
    private Integer price;
    /**
     * 市场价
     */
    private Integer marketPrice;
    /**
     * 成本价
     */
    private Integer costPrice;
    /**
     * 条形码
     */
    private String barCode;
    /**
     * 图片地址
     */
    @NotNull(message = "图片地址不能为空")
    private String picUrl;
    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer stock;
    /**
     * 预警预存
     */
    private Integer warnStock;
    /**
     * 商品重量,单位：kg 千克
     */
    private Double weight;
    /**
     * 商品体积,单位：m^3 平米
     */
    private Double volume;
    /**
     * 一级分销的佣金，单位：分
     */
    private Integer firstBrokeragePrice;
    /**
     * 二级分销的佣金，单位：分
     */
    private Integer secondBrokeragePrice;
    /**
     * 属性数组
     */
    private List<Property> properties;

    /**
     * 商品属性
     */

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Property {

        /**
         * 属性编号
         */
        private Long propertyId;
        /**
         * 属性名字
         */
        private String propertyName;
        /**
         * 属性值编号
         */
        private Long valueId;
        /**
         * 属性值名字
         */

        private String valueName;

    }

}
