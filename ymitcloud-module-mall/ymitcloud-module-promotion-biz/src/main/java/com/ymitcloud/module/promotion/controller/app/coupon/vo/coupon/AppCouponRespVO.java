package com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon;


import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import lombok.Data;

/** 
 * 用户 App - 优惠劵 */
@Data
public class AppCouponRespVO {

    /** 
     * 优惠劵编号*/
    private Long id;

    /** 
     * 优惠劵名*/
    private String name;

    /** 
     * 优惠劵状态*/ // 参见 CouponStatusEnum 枚举
    private Integer status;

    /** 
     * 是否设置满多少金额可用*/
    // 单位：分；0 - 不限制
    private Integer usePrice;

    /** 
     * 固定日期 - 生效开始时间")
     */
    private LocalDateTime validStartTime;

    /** 
     * 固定日期 - 生效结束时间")
     */
    private LocalDateTime validEndTime;

    /** 
     * 优惠类型*/
    private Integer discountType;

    /** 
     * 折扣百分比
     */
    private Integer discountPercent;

    /**
     *  优惠金额", example = "10")
     */
    @Min(value = 0, message = "优惠金额需要大于等于 0")
    private Integer discountPrice;

    /** 
     * 折扣上限
     */

    private Integer discountLimitPrice;

}
