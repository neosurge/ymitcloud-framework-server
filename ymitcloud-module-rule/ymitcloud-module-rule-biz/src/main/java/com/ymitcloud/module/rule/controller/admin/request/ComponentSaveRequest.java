package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentSaveRequest implements Serializable {
    /**
     * 组件名称
     */
    @NotBlank(message = "组件名称不能为空")
    private String componentName;
    /**
     * 组件类型 ( normal |  script )
     */
    @NotBlank(message = "组件类型不能为空")
    private String componentType;

    /**
     * 组件类别
     */
    @NotBlank(message = "组件类型不能为空")
    private String componentCategory;
    /**
     * 组件说明
     */
    private String componentDesc;

    /**
     * 组件参数列表
     */
    private List<ComponentParamSaveRequest> params;
}
