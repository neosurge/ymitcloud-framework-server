package com.ymitcloud.module.infra.controller.admin.job.vo.job;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 定时任务创建/修改 Request VO
 */
@Data
public class JobSaveReqVO {
    /**
     * 任务编号
     */
    private Long id;
    /**
     * 任务名称
     */
    @NotEmpty(message = "任务名称不能为空")
    private String name;
    /**
     * 处理器的名字
     */
    @NotEmpty(message = "处理器的名字不能为空")
    private String handlerName;
    /**
     * 处理器的参数
     */
    private String handlerParam;
    /**
     * CRON 表达式
     */
    @NotEmpty(message = "CRON 表达式不能为空")
    private String cronExpression;
    /**
     * 重试次数
     */
    @NotNull(message = "重试次数不能为空")
    private Integer retryCount;
    /**
     * 重试间隔
     */
    @NotNull(message = "重试间隔不能为空")
    private Integer retryInterval;
    /**
     * 监控超时时间
     */
    private Integer monitorTimeout;

}
