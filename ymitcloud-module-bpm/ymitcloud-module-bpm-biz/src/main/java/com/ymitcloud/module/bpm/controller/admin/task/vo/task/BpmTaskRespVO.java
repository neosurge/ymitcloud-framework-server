package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程任务的 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmTaskRespVO extends BpmTaskDonePageItemRespVO {

    /**
     * 任务定义的标识
     */

    private String definitionKey;

    /**
     * 审核的用户信息
     */
    private User assigneeUser;

    /**
     * 父任务ID
     */
    private String parentTaskId;

    /**
     * 子任务（由加签生成）
     */
    private List<BpmTaskRespVO> children;

    /**
     * 用户信息
     */
    @Data
    public static class User {
        /**
         * 用户编号
         */
        private Long id;
        /**
         * 用户昵称
         */
        private String nickname;
        /**
         * 部门编号
         */
        private Long deptId;
        /**
         * 部门名称
         */

        private String deptName;

    }
}
