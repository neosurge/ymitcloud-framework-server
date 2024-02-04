package com.ymitcloud.module.bpm.controller.admin.task.vo.instance;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 管理后台 - 流程实例的分页 Item Response VO
 */
@Data
public class BpmProcessInstancePageItemRespVO {
    /**
     * 流程实例的编号
     */
    private String id;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 流程定义的编号
     */
    private String processDefinitionId;
    /**
     * 流程分类-参见 bpm_model_category 数据字典
     */
    private String category;
    /**
     * 流程实例的状态-参见 bpm_process_instance_status
     */
    private Integer status;
    /**
     * 流程实例的结果-参见 bpm_process_instance_result
     */
    private Integer result;
    /**
     * 提交时间
     */
    private LocalDateTime createTime;
    /**
     * 结束时间
     */

    private LocalDateTime endTime;

    /**
     * 当前任务
     */
    private List<Task> tasks;


    /**
     * 流程任务
     */
    @Data
    public static class Task {
        /**
         * 流程任务的编号
         */
        private String id;
        /**
         * 任务名称
         */

        private String name;

    }

}
