package com.ymitcloud.module.statistics.controller.admin.trade.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 管理后台 - 交易状况 Request VO
 */
@Data
public class TradeTrendReqVO {
    /**
     * 时间范围
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)

    private LocalDateTime[] times;
}
