package com.ymitcloud.module.promotion.controller.app.bargain.vo.activity;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户 App - 砍价活动 Response VO
 */
@Data
public class AppBargainActivityRespVO {

    /**
     * 砍价活动编号
     */
    private Long id;

    /**
     * 砍价活动名称
     */
    private String name;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 商品 SPU 编号
     */
    private Long spuId;

    /**
     * 商品 SKU 编号
     */
    private Long skuId;

    /**
     * 砍价库存
     */
    private Integer stock;

    /**
     * 商品图片
     */
    private String picUrl;
    /**
     * 商品市场价，单位：分
     */
    private Integer marketPrice;

    /**
     * 砍价最低金额，单位：分
     */

    private Integer bargainMinPrice;

}
