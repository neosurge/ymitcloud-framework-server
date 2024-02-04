package com.ymitcloud.module.statistics.controller.admin.trade.vo;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.util.date.DateUtils;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.statistics.enums.TimeRangeTypeEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 交易订单量趋势统计 Request VO
 */
@Data
public class TradeOrderTrendReqVO {

    /** 日期范围类型 */

    @NotNull(message = "日期范围类型不能为空")
    @InEnum(value = TimeRangeTypeEnum.class, message = "日期范围类型，必须是 {value}")
    private Integer type;


    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    /**
     * 起始时间
     */
    private LocalDateTime beginTime;
    /**
     * 截止时间
     */
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)

    private LocalDateTime endTime;

}
