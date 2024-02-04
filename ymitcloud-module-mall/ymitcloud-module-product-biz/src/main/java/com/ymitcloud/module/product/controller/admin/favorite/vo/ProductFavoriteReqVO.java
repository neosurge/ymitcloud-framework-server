package com.ymitcloud.module.product.controller.admin.favorite.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商品收藏的单个 Response VO
 */
@Data
@ToString(callSuper = true)
public class ProductFavoriteReqVO extends ProductFavoriteBaseVO {
    /**
     * 商品 SPU 编号
     */

    @NotNull(message = "商品 SPU 编号不能为空")
    private Long spuId;
}
