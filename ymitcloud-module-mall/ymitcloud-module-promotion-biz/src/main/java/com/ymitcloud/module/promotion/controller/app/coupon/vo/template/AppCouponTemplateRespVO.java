package com.ymitcloud.module.promotion.controller.app.coupon.vo.template;


import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 用户 App - 优惠劵模板
 */
@Data
public class AppCouponTemplateRespVO {

    /**
     * 优惠劵模板编号
     */
    private Long id;

    /**
     * 优惠劵名
     */
    private String name;

    /**
     * 每人限领个数
     */ // -1 - 则表示不限制
    private Integer takeLimitCount;

    /**
     * 是否设置满多少金额可用
     */
    // 单位：分；0 - 不限制
    private Integer usePrice;

    /**
     * 生效日期类型
     */
    private Integer validityType;

    /**
     * 固定日期 - 生效开始时间
     */
    private LocalDateTime validStartTime;

    /**
     * 固定日期 - 生效结束时间
     */
    private LocalDateTime validEndTime;

    /**
     * 领取日期 - 开始天数
     */
    @Min(value = 0L, message = "开始天数必须大于 0")
    private Integer fixedStartTerm;

    /**
     * 领取日期 - 结束天数
     */
    @Min(value = 1L, message = "开始天数必须大于 1")
    private Integer fixedEndTerm;

    /**
     * 优惠类型
     */
    private Integer discountType;

    /**
     * 折扣百分比
     */
    private Integer discountPercent;

    /**
     * 优惠金额
     */
    @Min(value = 0, message = "优惠金额需要大于等于 0")
    private Integer discountPrice;

    /**
     * 折扣上限
     */

    private Integer discountLimitPrice;

    // ========== 用户相关字段 ==========


    /**
     * 是否可以领取
     */

    private Boolean canTake;

}
