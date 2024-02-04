package com.ymitcloud.module.mp.controller.admin.statistics.vo;



import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 管理后台 - 获得统计数据 Request VO */
@Data
public class MpStatisticsGetReqVO {

    /** 公众号账号的编号*/
    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

    /** 
     * 查询时间范围
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotNull(message = "查询时间范围不能为空")
    private LocalDateTime[] date;

}
