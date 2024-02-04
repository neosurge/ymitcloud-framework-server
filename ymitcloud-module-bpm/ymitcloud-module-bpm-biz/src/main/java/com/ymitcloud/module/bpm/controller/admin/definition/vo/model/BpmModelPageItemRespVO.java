package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程模型的分页的每一项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmModelPageItemRespVO extends BpmModelBaseVO {

    /**
     * 编号
     */
    private String id;
    /**
     * 表单名字
     */
    private String formName;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 最新部署的流程定义
     */
    private ProcessDefinition processDefinition;


    /**
     * 流程定义
     */
    @Data
    public static class ProcessDefinition {

        /**
         * 编号
         */
        private String id;

        /**
         * 版本
         */
        private Integer version;

        /**
         * 部署时间
         */
        private LocalDateTime deploymentTime;
        /**
         * 中断状态-参见 SuspensionState 枚举
         */

        private Integer suspensionState;

    }

}
