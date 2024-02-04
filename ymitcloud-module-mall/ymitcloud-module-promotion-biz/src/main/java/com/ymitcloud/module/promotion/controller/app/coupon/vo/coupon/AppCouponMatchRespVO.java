package com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon;


import lombok.Data;

/**
 * 用户 App - 优惠劵
 */
@Data
public class AppCouponMatchRespVO extends AppCouponRespVO {

    /**
     * 是否匹配
     */
    private Boolean match;

    /**
     * 匹配条件的提示
     */

    private String description;

}
