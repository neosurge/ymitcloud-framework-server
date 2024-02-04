package com.ymitcloud.module.bpm.controller.admin.definition.vo.rule;


import jakarta.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程任务分配规则的创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmTaskAssignRuleCreateReqVO extends BpmTaskAssignRuleBaseVO {


    /**
     * 流程模型的编号
     */
    @NotEmpty(message = "流程模型的编号不能为空")
    private String modelId;

    /**
     * 流程任务定义的编号
     */

    @NotEmpty(message = "流程任务定义的编号不能为空")
    private String taskDefinitionKey;

}
