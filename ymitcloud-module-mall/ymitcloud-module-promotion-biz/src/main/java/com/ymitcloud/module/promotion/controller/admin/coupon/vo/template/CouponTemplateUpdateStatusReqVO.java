package com.ymitcloud.module.promotion.controller.admin.coupon.vo.template;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 管理后台 - 优惠劵模板更新状态 Request VO
 */
@Data
public class CouponTemplateUpdateStatusReqVO {

    /** 
     * 优惠劵模板编号
     */
    @NotNull(message = "优惠劵模板编号不能为空")
    private Long id;

    /** 
     * 状态
     */

    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
