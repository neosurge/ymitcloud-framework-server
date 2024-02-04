package com.ymitcloud.module.bpm.controller.admin.task.vo.instance;


import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 流程实例的创建 Request VO
 */
@Data
public class BpmProcessInstanceCreateReqVO {
    /**
     * 流程定义的编号
     */
    @NotEmpty(message = "流程定义编号不能为空")
    private String processDefinitionId;
    /**
     * 变量实例
     */
    private Map<String, Object> variables;

    /**
     * 提前指派的审批人
     */

    private Map<String, List<Long>> assignee;

}
