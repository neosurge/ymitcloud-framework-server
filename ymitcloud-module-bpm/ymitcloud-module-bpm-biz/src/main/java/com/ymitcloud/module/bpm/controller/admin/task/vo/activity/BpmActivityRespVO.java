package com.ymitcloud.module.bpm.controller.admin.task.vo.activity;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 流程活动的 Response VO
 */
@Data
public class BpmActivityRespVO {
    /**
     * 流程活动的标识
     */
    private String key;
    /**
     * 流程活动的类型
     */
    private String type;
    /**
     * 流程活动的开始时间
     */
    private LocalDateTime startTime;
    /**
     * 流程活动的结束时间
     */
    private LocalDateTime endTime;
    /**
     * 关联的流程任务的编号-关联的流程任务，只有 UserTask 等类型才有
     */

    private String taskId;

}
