package com.ymitcloud.module.trade.controller.app.cart.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - 购物车添加购物项 Request VO
 */
@Data
public class AppCartAddReqVO {

    /** 商品 SKU 编号 */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;

    /** 新增商品数量 */

    @NotNull(message = "数量不能为空")
    private Integer count;

}
