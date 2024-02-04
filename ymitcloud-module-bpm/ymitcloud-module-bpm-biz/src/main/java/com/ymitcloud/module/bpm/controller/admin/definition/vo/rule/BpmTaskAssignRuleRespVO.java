package com.ymitcloud.module.bpm.controller.admin.definition.vo.rule;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程任务分配规则的 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmTaskAssignRuleRespVO extends BpmTaskAssignRuleBaseVO {

    /**
     * 任务分配规则的编号
     */
    private Long id;
    /**
     * 流程模型的编号
     */
    private String modelId;
    /**
     * 流程定义的编号
     */
    private String processDefinitionId;
    /**
     * 流程任务定义的编号
     */
    private String taskDefinitionKey;
    /**
     * 流程任务定义的名字
     */

    private String taskDefinitionName;

}
