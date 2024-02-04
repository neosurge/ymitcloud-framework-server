package com.ymitcloud.module.bpm.controller.admin.definition.vo.process;


import java.util.List;

import com.ymitcloud.module.bpm.enums.definition.BpmModelFormTypeEnum;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 流程定义 Response VO
 */
@Data
public class BpmProcessDefinitionRespVO {

    /**
     * 编号
     */
    private String id;

    /**
     * 版本
     */
    private Integer version;
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
     * 表单的配置-JSON 字符串。在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */
    private String formConf;
    /**
     * 表单项的数组-JSON 字符串的数组。在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */
    private List<String> formFields;
    /**
     * 自定义表单的提交路径，使用 Vue 的路由地址-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */
    private String formCustomCreatePath;
    /**
     * 自定义表单的查看路径，使用 Vue 的路由地址-在表单类型为 {@link BpmModelFormTypeEnum#CUSTOM} 时，必须非空
     */
    private String formCustomViewPath;
    /**
     * 中断状态-参见 SuspensionState 枚举
     */

    private Integer suspensionState;

}
