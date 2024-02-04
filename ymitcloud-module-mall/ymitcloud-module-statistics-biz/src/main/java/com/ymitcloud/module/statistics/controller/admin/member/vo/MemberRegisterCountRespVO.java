package com.ymitcloud.module.statistics.controller.admin.member.vo;

import com.fasterxml.jackson.annotation.JsonFormat;



import lombok.Data;

import java.time.LocalDate;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static com.ymitcloud.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;


/** 管理后台 - 会员注册数量 */

@Data
public class MemberRegisterCountRespVO {

    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY, timezone = TIME_ZONE_DEFAULT)

    /** 日期*/
    private LocalDate date;

    /** 数量*/

    private Integer count;

}
