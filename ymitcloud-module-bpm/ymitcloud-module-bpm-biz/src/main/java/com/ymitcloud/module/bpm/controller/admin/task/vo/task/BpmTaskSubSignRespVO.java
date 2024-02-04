package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import lombok.Data;

/**
 * 管理后台 - 减签流程任务的 Response VO
 */
@Data
public class BpmTaskSubSignRespVO {
    /**
     * 审核的用户信息
     */
    private BpmTaskRespVO.User assigneeUser;
    /**
     * 任务 ID
     */
    private String id;
    /**
     * 任务名称
     */

    private String name;
}
