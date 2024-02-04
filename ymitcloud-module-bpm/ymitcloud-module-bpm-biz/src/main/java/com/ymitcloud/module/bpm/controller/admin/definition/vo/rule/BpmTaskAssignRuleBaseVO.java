package com.ymitcloud.module.bpm.controller.admin.definition.vo.rule;


import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 流程任务分配规则 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class BpmTaskAssignRuleBaseVO {


    /**
     * 规则类型
     */
    @NotNull(message = "规则类型不能为空")
    private Integer type;
    /**
     * 规则值数组
     */

    @NotNull(message = "规则值数组不能为空")
    private Set<Long> options;

}
