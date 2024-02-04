package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 不通过流程任务的 Request VO
 */
@Data
public class BpmTaskRejectReqVO {
    /**
     * 任务编号
     */
    @NotEmpty(message = "任务编号不能为空")
    private String id;
    /**
     * 审批意见
     */

    @NotEmpty(message = "审批意见不能为空")
    private String reason;

}
