package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 委派流程任务的 Request VO
 */
@Data
public class BpmTaskDelegateReqVO {
    /**
     * 任务编号
     */
    @NotEmpty(message = "任务编号不能为空")
    private String id;
    /**
     * 被委派人 ID
     */
    @NotNull(message = "被委派人 ID 不能为空")
    private Long delegateUserId;
    /**
     * 委派原因
     */

    @NotEmpty(message = "委派原因不能为空")
    private String reason;

}
