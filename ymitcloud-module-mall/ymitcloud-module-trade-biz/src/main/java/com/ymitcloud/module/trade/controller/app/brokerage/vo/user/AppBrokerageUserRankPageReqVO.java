package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/** 
 * 应用 App - 分销用户排行 Request VO
 */
@Data
public class AppBrokerageUserRankPageReqVO extends PageParam {

    /** 开始 + 结束时间*/

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotEmpty(message = "时间不能为空")
    private LocalDateTime[] times;

}
