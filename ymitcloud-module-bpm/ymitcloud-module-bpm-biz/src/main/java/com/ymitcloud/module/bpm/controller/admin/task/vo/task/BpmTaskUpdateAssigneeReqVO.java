package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 流程任务的更新负责人的 Request VO
 */
@Data
public class BpmTaskUpdateAssigneeReqVO {

    /**
     * 任务编号
     */
    @NotEmpty(message = "任务编号不能为空")
    private String id;
    /**
     * 新审批人的用户编号
     */

    @NotNull(message = "新审批人的用户编号不能为空")
    private Long assigneeUserId;

}
