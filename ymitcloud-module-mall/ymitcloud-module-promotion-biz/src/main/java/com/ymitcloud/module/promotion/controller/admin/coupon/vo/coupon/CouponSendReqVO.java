package com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon;


import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/** 
 * 管理后台 - 优惠劵发放 Request VO
 */

@Data
@ToString(callSuper = true)
public class CouponSendReqVO {


    /** 
     * 优惠劵模板编号
     */
    @NotNull(message = "优惠劵模板编号不能为空")
    private Long templateId;

    /** 
     * 用户编号列表
     */

    @NotEmpty(message = "用户编号列表不能为空")
    private Set<Long> userIds;

}
