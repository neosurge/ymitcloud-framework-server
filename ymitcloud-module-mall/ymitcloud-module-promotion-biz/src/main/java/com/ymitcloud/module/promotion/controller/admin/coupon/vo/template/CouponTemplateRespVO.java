package com.ymitcloud.module.promotion.controller.admin.coupon.vo.template;


import java.time.LocalDateTime;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 *  管理后台 - 优惠劵模板 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponTemplateRespVO extends CouponTemplateBaseVO {


    /**
     *  模板编号
     */
    private Long id;

    /** 
     * 状态
     */
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    /** 
     * 领取优惠券的数量
     */
    private Integer takeCount;

    /** 
     * 使用优惠券的次数
     */
    private Integer useCount;

    /** 
     * 创建时间
     */

    private LocalDateTime createTime;

}
