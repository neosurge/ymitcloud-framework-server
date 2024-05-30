package com.ymit.module.infra.controller.admin.job.vo.job;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.infra.enums.DictTypeConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 定时任务 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class JobRespVO {
    /**
     * 任务编号
     */
    @ExcelProperty("任务编号")
    private Long id;
    /**
     * 任务名称
     */
    @ExcelProperty("任务名称")
    private String name;
    /**
     * 任务状态
     */
    @ExcelProperty(value = "任务状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.JOB_STATUS)
    private Integer status;
    /**
     * 处理器的名字
     */
    @ExcelProperty("处理器的名字")
    private String handlerName;
    /**
     * 处理器的参数
     */
    @ExcelProperty("处理器的参数")
    private String handlerParam;
    /**
     * CRON 表达式
     */
    @ExcelProperty("CRON 表达式")
    private String cronExpression;
    /**
     * 重试次数
     */
    @NotNull(message = "重试次数不能为空")
    private Integer retryCount;
    /**
     * 重试间隔
     */
    private Integer retryInterval;
    /**
     * 监控超时时间
     */
    @ExcelProperty("监控超时时间")
    private Integer monitorTimeout;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
