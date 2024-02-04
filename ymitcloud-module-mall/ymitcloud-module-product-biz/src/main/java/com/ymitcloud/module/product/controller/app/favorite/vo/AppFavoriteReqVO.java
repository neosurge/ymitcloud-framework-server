package com.ymitcloud.module.product.controller.app.favorite.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 APP - 商品收藏的单个 Request VO
 */
@Data
public class AppFavoriteReqVO {
    /**
     * 商品 SPU 编号
     */

    @NotNull(message = "商品 SPU 编号不能为空")
    private Long spuId;

}
