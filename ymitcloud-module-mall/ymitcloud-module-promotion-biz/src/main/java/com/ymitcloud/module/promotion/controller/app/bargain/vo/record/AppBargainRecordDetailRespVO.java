package com.ymitcloud.module.promotion.controller.app.bargain.vo.record;


import lombok.Data;

/**
 * 用户 App - 砍价记录的明细
 */

@Data
public class AppBargainRecordDetailRespVO {

    public static final int HELP_ACTION_NONE = 1; // 帮砍动作 - 未帮砍，可以帮砍
    public static final int HELP_ACTION_FULL = 2; // 帮砍动作 - 未帮砍，无法帮砍（可帮砍次数已满)
    public static final int HELP_ACTION_SUCCESS = 3; // 帮砍动作 - 已帮砍

    // ========== 砍价记录 ==========


    /**
     * 砍价记录编号
     */
    private Long id;

    /** 用户编号 */
    private Long userId;

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
     * 砍价起始价格，单位：分
     */
    private Integer bargainFirstPrice;

    /**
     * 当前砍价，单位：分
     */
    private Integer bargainPrice;

    /**
     * 砍价记录状态
     */

    private Integer status;

    // ========== 订单相关 ========== 注意：只有是自己的砍价记录，才会返回，保证隐私性


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

    // ========== 助力记录 ==========

    private Integer helpAction;

}
