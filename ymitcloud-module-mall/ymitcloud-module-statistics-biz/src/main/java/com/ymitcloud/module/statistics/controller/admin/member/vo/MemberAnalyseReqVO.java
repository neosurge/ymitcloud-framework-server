package com.ymitcloud.module.statistics.controller.admin.member.vo;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.util.date.DateUtils;

import lombok.Data;

/** 
 * 管理后台 - 会员分析 Request VO
 */
@Data
public class MemberAnalyseReqVO {

   
    /** 
     * 时间范围
     */
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)

    private LocalDateTime[] times;

}
