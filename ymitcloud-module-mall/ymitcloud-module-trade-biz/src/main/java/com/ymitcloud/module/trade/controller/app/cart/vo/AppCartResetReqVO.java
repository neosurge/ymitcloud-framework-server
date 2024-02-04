package com.ymitcloud.module.trade.controller.app.cart.vo;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 用户 App - 购物车重置 Request VO
 */
@Data
public class AppCartResetReqVO {

    /** 编号*/
    @NotNull(message = "编号不能为空")
    private Long id;

    /** 商品 SKU 编号*/
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;

    /** 商品数量*/

    @NotNull(message = "数量不能为空")
    @Min(message = "数量必须大于 0", value = 1L)
    private Integer count;

}
