package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import lombok.Data;

/**
 * 管理后台 - 流程任务的精简 Response VO
 */
@Data
public class BpmTaskSimpleRespVO {
    /**
     * 任务定义的标识
     */
    private String definitionKey;
    /**
     * 任务名词
     */

    private String name;

}
