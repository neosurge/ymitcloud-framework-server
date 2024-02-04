package com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static com.ymitcloud.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.common.PromotionDiscountTypeEnum;
import com.ymitcloud.module.promotion.enums.common.PromotionProductScopeEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
* 优惠劵 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CouponBaseVO {

    // ========== 基本信息 BEGIN ==========

    /** 
     * 优惠劵模板编号
     */
    @NotNull(message = "优惠劵模板编号不能为空")
    private Long templateId;

    /** 
     * 优惠劵名
     */
    @NotNull(message = "优惠劵名不能为空")
    private String name;

    /** 
     * 优惠码状态
     */

    private Integer status;

    // ========== 基本信息 END ==========

    // ========== 领取情况 BEGIN ==========

    /** 
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 
     * 领取方式
     */

    @NotNull(message = "领取方式不能为空")
    private Integer takeType;
    // ========== 领取情况 END ==========

    // ========== 使用规则 BEGIN ==========

    /** 
     * 是否设置满多少金额可用
     */
    @NotNull(message = "是否设置满多少金额可用不能为空")
    private Integer usePrice;

    /** 
     * 固定日期 - 生效开始时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private LocalDateTime validStartTime;


    /** 
     * 固定日期 - 生效结束时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private LocalDateTime validEndTime;


    /** 
     * 商品范围
     */

    @NotNull(message = "商品范围不能为空")
    @InEnum(PromotionProductScopeEnum.class)
    private Integer productScope;


    /** 
     * 商品范围编号的数组
     */

    private List<Long> productScopeValues;
    // ========== 使用规则 END ==========

    // ========== 使用效果 BEGIN ==========

    /** 
     * 优惠类型
     */

    @NotNull(message = "优惠类型不能为空")
    @InEnum(PromotionDiscountTypeEnum.class)
    private Integer discountType;


    /** 
     * 折扣百分比
     */
    private Integer discountPercent;

    /** 
     * 优惠金额
     */
    @Min(value = 0, message = "优惠金额需要大于等于 0")
    private Integer discountPrice;

    /** 
     * 折扣上限
     */

    private Integer discountLimitPrice;
    // ========== 使用效果 END ==========

    // ========== 使用情况 BEGIN ==========


    /** 
     * 使用订单号
     */
    private Long useOrderId;

    /** 
     * 使用时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private LocalDateTime useTime;

    // ========== 使用情况 END ==========

}
