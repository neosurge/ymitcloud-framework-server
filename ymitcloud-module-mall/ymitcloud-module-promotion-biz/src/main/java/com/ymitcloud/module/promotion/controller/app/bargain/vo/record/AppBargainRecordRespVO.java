package com.ymitcloud.module.promotion.controller.app.bargain.vo.record;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户 App - 砍价记录的
 */
@Data
public class AppBargainRecordRespVO {

    /**
     * 砍价记录编号
     */
    private Long id;

    /**
     * 商品 SPU 编号
     */
    private Long spuId;
    /**
     * 商品 SKU 编号
     */
    private Long skuId;

    /**
     * 活动编号
     */
    private Long activityId;

    /**
     * 砍价记录状态
     */
    private Integer status;

    /**
     * 当前价格
     */

    private Integer bargainPrice;

    // ========== 活动相关 ==========


    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 商品图片
     */

    private String picUrl;

    // ========== 订单相关 ==========


    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 支付状态
     */
    private Boolean payStatus;

    /**
     * 支付订单编号
     */

    private Long payOrderId;

}
