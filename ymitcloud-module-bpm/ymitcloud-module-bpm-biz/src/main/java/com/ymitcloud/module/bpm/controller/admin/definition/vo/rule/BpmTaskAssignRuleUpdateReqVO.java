package com.ymitcloud.module.bpm.controller.admin.definition.vo.rule;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 流程任务分配规则的更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmTaskAssignRuleUpdateReqVO extends BpmTaskAssignRuleBaseVO {

    /**
     * 任务分配规则的编号
     */

    @NotNull(message = "任务分配规则的编号不能为空")
    private Long id;

}
