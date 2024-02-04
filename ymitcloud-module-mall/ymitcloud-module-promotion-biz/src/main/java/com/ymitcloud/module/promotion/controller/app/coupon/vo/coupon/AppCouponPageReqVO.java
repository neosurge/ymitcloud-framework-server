package com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.coupon.CouponStatusEnum;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 用户 App - 优惠劵分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCouponPageReqVO extends PageParam {


    /**
     * 优惠劵状态
     */

    @InEnum(value = CouponStatusEnum.class, message = "优惠劵状态，必须是 {value}")
    private Integer status;

}
