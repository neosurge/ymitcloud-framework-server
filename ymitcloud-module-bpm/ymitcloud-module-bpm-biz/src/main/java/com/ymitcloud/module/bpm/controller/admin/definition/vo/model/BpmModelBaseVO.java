package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;


import com.ymitcloud.module.bpm.enums.definition.BpmModelFormTypeEnum;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 流程模型 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BpmModelBaseVO {

    /**
     * 流程标识
     */
    @NotEmpty(message = "流程标识不能为空")
    private String key;

    /**
     * 流程名称
     */
    @NotEmpty(message = "流程名称不能为空")
    private String name;

    /**
     * 流程描述
     */
    private String description;

    /**
     * 流程分类-参见 bpm_model_category 数据字典
     */
    @NotEmpty(message = "流程分类不能为空")
    private String category;

    /**
     * 表单类型-参见 bpm_model_form_type 数据字典
     */
    private Integer formType;
    /**
     * 表单编号-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */
    private Long formId;
    /**
     * 自定义表单的提交路径，使用 Vue 的路由地址-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */
    private String formCustomCreatePath;
    /**
     * 自定义表单的查看路径，使用 Vue 的路由地址-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */

    private String formCustomViewPath;

}
