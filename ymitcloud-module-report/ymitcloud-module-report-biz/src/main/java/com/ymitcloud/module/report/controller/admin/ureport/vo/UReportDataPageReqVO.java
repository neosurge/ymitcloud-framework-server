package com.ymitcloud.module.report.controller.admin.ureport.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - Ureport2 报表分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UReportDataPageReqVO extends PageParam {


    /** 文件名称 */
    private String name;

    /** 状态 */
    private Integer status;

    /** 备注 */
    private String remark;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}