package com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 *  管理后台 - 优惠劵 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponRespVO extends CouponBaseVO {


    /** 
     * 优惠劵编号
     */
    private Long id;

    /** 
     * 创建时间
     */

    private LocalDateTime createTime;

}
