package com.ymit.module.infra.controller.admin.job.vo.log;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 定时任务日志分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobLogPageReqVO extends PageParam {
    @Serial
    private static final long serialVersionUID = 4755532994453496868L;
    /**
     * 任务编号
     */
    private Long jobId;
    /**
     * 处理器的名字，模糊匹配
     */
    private String handlerName;
    /**
     * 开始执行时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginTime;
    /**
     * 结束执行时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;
    /**
     * 任务状态，参见 JobLogStatusEnum 枚举
     */
    private Integer status;

}
