package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 流程任务的 Running 进行中的分页项 Response VO
 */
@Data
public class BpmTaskTodoPageItemRespVO {
    /**
     * 任务编号
     */
    private String id;
    /**
     * 任务名字
     */
    private String name;
    /**
     * 接收时间
     */
    private LocalDateTime claimTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 激活状态-参见 SuspensionState 枚举
     */

    private Integer suspensionState;

    /**
     * 所属流程实例
     */
    private ProcessInstance processInstance;


    /**
     * 流程实例
     */
    @Data
    public static class ProcessInstance {
        /**
         * 流程实例编号
         */
        private String id;
        /**
         * 流程实例名称
         */
        private String name;
        /**
         * 发起人的用户编号
         */
        private Long startUserId;
        /**
         * 发起人的用户昵称
         */
        private String startUserNickname;
        /**
         * 流程定义的编号
         */

        private String processDefinitionId;

    }

}
