package com.ymitcloud.module.promotion.controller.admin.discount.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.common.PromotionDiscountTypeEnum;

import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
* 限时折扣活动 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DiscountActivityBaseVO {


    /** 
     * 活动标题
     */
    @NotNull(message = "活动标题不能为空")
    private String name;

    /** 
     * 开始时间
     */

    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;


    /** 
     * 结束时间
     */

    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;


    /** 
     * 备注
     */
    private String remark;

    /** 
     * 商品
     */
    @Data
    public static class Product {

        /** 
         * 商品 SPU 编号
         */
        @NotNull(message = "商品 SPU 编号不能为空")
        private Long spuId;

        /** 
         * 商品 SKU 编号
         */
        @NotNull(message = "商品 SKU 编号不能为空")
        private Long skuId;

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

        @AssertTrue(message = "折扣百分比需要大于等于 1，小于等于 99")
        @JsonIgnore
        public boolean isDiscountPercentValid() {
            return ObjectUtil.notEqual(discountType, PromotionDiscountTypeEnum.PERCENT.getType())
                    || (discountPercent != null && discountPercent >= 1 && discountPercent<= 99);
        }

        @AssertTrue(message = "优惠金额不能为空")
        @JsonIgnore
        public boolean isDiscountPriceValid() {
            return ObjectUtil.notEqual(discountType, PromotionDiscountTypeEnum.PRICE.getType())
                    || discountPrice != null;
        }

    }
}
