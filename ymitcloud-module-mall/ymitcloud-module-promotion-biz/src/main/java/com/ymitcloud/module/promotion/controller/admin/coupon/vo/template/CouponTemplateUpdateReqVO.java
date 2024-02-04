package com.ymitcloud.module.promotion.controller.admin.coupon.vo.template;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 优惠劵模板更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponTemplateUpdateReqVO extends CouponTemplateBaseVO {


    /** 
     * 模板编号
     */

    @NotNull(message = "模板编号不能为空")
    private Long id;

}
