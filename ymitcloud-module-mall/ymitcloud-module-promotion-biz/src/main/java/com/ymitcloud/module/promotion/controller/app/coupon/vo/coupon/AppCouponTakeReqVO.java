package com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - 优惠劵领取 Request VO
 */
@Data
public class AppCouponTakeReqVO {

    /**
     * 优惠劵模板编号
     */

    @NotNull(message = "优惠劵模板编号不能为空")
    private Long templateId;

}
