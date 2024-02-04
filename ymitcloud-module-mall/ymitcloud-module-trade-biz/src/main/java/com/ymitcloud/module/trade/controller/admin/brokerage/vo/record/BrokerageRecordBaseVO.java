package com.ymitcloud.module.trade.controller.admin.brokerage.vo.record;



import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;


/**
 * 佣金记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BrokerageRecordBaseVO {


    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 业务编号*/
    @NotEmpty(message = "业务编号不能为空")
    private String bizId;

    /** 业务类型*/
    @NotNull(message = "业务类型不能为空")
    private Integer bizType;

    /** 标题*/
    @NotEmpty(message = "标题不能为空")
    private String title;

    /** 金额*/
    @NotNull(message = "金额不能为空")
    private Integer price;

    /** 当前总佣金*/
    @NotNull(message = "当前总佣金不能为空")
    private Integer totalPrice;

    /** 说明*/
    @NotNull(message = "说明不能为空")
    private String description;

    /** 状态*/
    @NotNull(message = "状态不能为空")
    private Integer status;

    /** 冻结时间（天）*/
    @NotNull(message = "冻结时间（天）不能为空")
    private Integer frozenDays;

    /** 
     * 解冻时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime unfreezeTime;

    /** 
     * 来源用户等级
     */
    private Integer sourceUserLevel;

    /** 
     * 来源用户编号
     */

    private Long sourceUserId;
}
