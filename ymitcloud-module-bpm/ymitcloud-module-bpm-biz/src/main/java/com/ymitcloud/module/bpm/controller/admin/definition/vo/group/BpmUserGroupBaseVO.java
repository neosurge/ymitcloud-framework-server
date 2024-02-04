package com.ymitcloud.module.bpm.controller.admin.definition.vo.group;


import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户组 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BpmUserGroupBaseVO {
    /**
     * 组名
     */
    @NotNull(message = "组名不能为空")
    private String name;

    /**
     * 描述
     */
    @NotNull(message = "描述不能为空")
    private String description;
    /**
     * 成员编号数组
     */
    @NotNull(message = "成员编号数组不能为空")
    private Set<Long> memberUserIds;

    /**
     * 状态
     */

    @NotNull(message = "状态不能为空")
    private Integer status;

}
