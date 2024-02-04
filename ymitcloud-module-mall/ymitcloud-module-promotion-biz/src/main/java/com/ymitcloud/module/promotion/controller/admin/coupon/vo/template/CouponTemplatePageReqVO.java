package com.ymitcloud.module.promotion.controller.admin.coupon.vo.template;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.common.PromotionProductScopeEnum;
import com.ymitcloud.module.promotion.enums.coupon.CouponTakeTypeEnum;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  管理后台 - 优惠劵模板分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponTemplatePageReqVO extends PageParam {


    /**
     *  优惠劵名
     */
    private String name;

    /**
     *  状态
     */
    private Integer status;

    /** 
     * 优惠类型
     */
    private Integer discountType;

    /** 
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /** 
     * 可以领取的类型
     */
    @InEnum(value = CouponTakeTypeEnum.class, message = "可以领取的类型，必须是 {value}")
    private List<Integer> canTakeTypes;

    /** 
     * 商品范围
     */
    @InEnum(value = PromotionProductScopeEnum.class, message = "商品范围，必须是 {value}")
    private Integer productScope;

    /** 
     * 商品范围编号
     */

    private Long productScopeValue;

}
