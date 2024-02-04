package com.ymitcloud.module.promotion.controller.admin.combination.vo.product;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 拼团商品 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CombinationProductBaseVO {


    /** 
     * 商品 spuId
     */
    @NotNull(message = "商品 spuId 不能为空")
    private Long spuId;

    /** 
     * 商品 skuId
     */
    @NotNull(message = "商品 skuId 不能为空")
    private Long skuId;

    /** 
     * 拼团价格，单位分
     */

    @NotNull(message = "拼团价格不能为空")
    private Integer combinationPrice;

}
