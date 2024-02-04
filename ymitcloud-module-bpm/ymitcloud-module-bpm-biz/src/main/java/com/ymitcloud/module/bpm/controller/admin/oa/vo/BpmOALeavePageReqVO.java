package com.ymitcloud.module.bpm.controller.admin.oa.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 请假申请分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmOALeavePageReqVO extends PageParam {


    /**
     * 状态-参见 bpm_process_instance_result 枚举
     */
    private Integer result;
    /**
     * 请假类型-参见 bpm_oa_type
     */
    private Integer type;
    /**
     * 原因-模糊匹配
     */
    private String reason;

    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)

    private LocalDateTime[] createTime;

}
