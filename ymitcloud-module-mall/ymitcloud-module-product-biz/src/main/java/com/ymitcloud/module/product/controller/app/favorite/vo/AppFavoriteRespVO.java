package com.ymitcloud.module.product.controller.app.favorite.vo;


import lombok.Data;

/**
 * 用户 App - 商品收藏 Response VO
 */
@Data
public class AppFavoriteRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 商品 SPU 编号
     */

    private Long spuId;

    // ========== 商品相关字段 ==========


    /**
     * 商品 SPU 名称
     */
    private String spuName;
    /**
     * 商品封面图
     */
    private String picUrl;

    /**
     * 商品单价
     */

    private Integer price;

}
