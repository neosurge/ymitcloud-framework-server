package com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 优惠劵分页的每一项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponPageItemRespVO extends CouponRespVO {


    /** 
     * 用户昵称
     */

    private String nickname;

}
