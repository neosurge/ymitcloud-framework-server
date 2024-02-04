package com.ymitcloud.module.promotion.controller.admin.reward.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.common.PromotionConditionTypeEnum;

import cn.hutool.core.collection.CollUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;


/**
* 满减送活动 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RewardActivityBaseVO {


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
    @Future(message = "结束时间必须大于当前时间")
    private LocalDateTime endTime;


    /** 
     * 备注
     */
    private String remark;

    /** 
     * 条件类型
     */

    @NotNull(message = "条件类型不能为空")
    @InEnum(value = PromotionConditionTypeEnum.class, message = "条件类型必须是 {value}")
    private Integer conditionType;


    /** 
     * 商品范围
     */

    @NotNull(message = "商品范围不能为空")
    @InEnum(value = PromotionConditionTypeEnum.class, message = "商品范围必须是 {value}")
    private Integer productScope;


    /** 
     * 商品 SPU 编号的数组
     */

    private List<Long> productSpuIds;

    /**
     * 优惠规则的数组
     */
    @Valid // 校验下子对象
    private List<Rule> rules;


    /** 
     * 优惠规则")
     */
    @Data
    public static class Rule {

        /** 
         * 优惠门槛
         */
        @Min(value = 1L, message = "优惠门槛必须大于等于 1")
        private Integer limit;

        /** 
         * 优惠价格
         */
        @Min(value = 1L, message = "优惠价格必须大于等于 1")
        private Integer discountPrice;

        /** 
         * 是否包邮
         */
        private Boolean freeDelivery;

        /** 
         * 赠送的积分
         */
        @Min(value = 1L, message = "赠送的积分必须大于等于 1")
        private Integer point;

        /** 
         * 赠送的优惠劵编号的数组
         */
        private List<Long> couponIds;

        /** 
         * 赠送的优惠券数量的数组
         */

        private List<Integer> couponCounts;

        @AssertTrue(message = "优惠劵和数量必须一一对应")
        @JsonIgnore
        public boolean isCouponCountsValid() {
            return CollUtil.size(couponCounts) == CollUtil.size(couponCounts);
        }

    }

}
