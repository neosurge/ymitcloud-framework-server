package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 减签流程任务的 Request VO
 */
@Data
public class BpmTaskSubSignReqVO {
    /**
     * 被减签的任务 ID
     */
    @NotEmpty(message = "任务编号不能为空")
    private String id;
    /**
     * 加签原因
     */

    @NotEmpty(message = "加签原因不能为空")
    private String reason;
}
