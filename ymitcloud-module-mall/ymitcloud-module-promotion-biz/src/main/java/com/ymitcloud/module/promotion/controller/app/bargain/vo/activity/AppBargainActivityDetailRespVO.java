package com.ymitcloud.module.promotion.controller.app.bargain.vo.activity;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户 App - 砍价活动的明细 Response VO
 */
@Data
public class AppBargainActivityDetailRespVO {

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
     * 商品价格，单位：分
     */
    private Integer price;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 砍价库存
     */
    private Integer stock;

    /**
     * 商品图片
     */ // 从 SPU 的 picUrl 读取
    private String picUrl;

    /**
     * 商品市场价，单位：分
     */ // 从 SPU 的 marketPrice 读取
    private Integer marketPrice;

    /**
     * 商品单位
     */ // 从 SPU 的 unit 读取，然后转换
    private String unitName;

    /**
     * 砍价起始价格，单位：分
     */
    private Integer bargainFirstPrice;

    /**
     * 砍价最低金额，单位：分
     */
    private Integer bargainMinPrice;

    /**
     * 砍价成功数量
     */

    private Integer successUserCount;

}
