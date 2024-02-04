package com.ymitcloud.module.promotion.controller.admin.seckill.vo.product;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 秒杀参与商品 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 *
 * @author HUIHUI
 */
@Data
public class SeckillProductBaseVO {


    /**
     * 商品sku_id
     */
    @NotNull(message = "商品sku_id不能为空")
    private Long skuId;

    /**
     * 秒杀金额，单位：分
     */
    @NotNull(message = "秒杀金额，单位：分不能为空")
    private Integer seckillPrice;

    /**
     * 秒杀库存
     */

    @NotNull(message = "秒杀库存不能为空")
    private Integer stock;

}
