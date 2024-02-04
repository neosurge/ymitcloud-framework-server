package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 回退流程任务的 Request VO
 */
@Data
public class BpmTaskReturnReqVO {
    /**
     * 任务编号
     */
    @NotEmpty(message = "任务编号不能为空")
    private String id;
    /**
     * 回退到的任务 Key
     */
    @NotEmpty(message = "回退到的任务 Key 不能为空")
    private String targetDefinitionKey;
    /**
     * 回退意见
     */

    @NotEmpty(message = "回退意见不能为空")
    private String reason;

}
