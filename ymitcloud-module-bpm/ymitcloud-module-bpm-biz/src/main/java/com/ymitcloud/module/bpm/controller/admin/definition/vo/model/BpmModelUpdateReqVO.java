package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;


import com.ymitcloud.module.bpm.enums.definition.BpmModelFormTypeEnum;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 流程模型的更新 Request VO
 */
@Data
public class BpmModelUpdateReqVO {

    /**
     * 编号
     */
    @NotEmpty(message = "编号不能为空")
    private String id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程描述
     */
    private String description;

    /**
     * 流程分类-参见 bpm_model_category 数据字典
     */
    private String category;

    /**
     * BPMN XML
     */
    private String bpmnXml;

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
