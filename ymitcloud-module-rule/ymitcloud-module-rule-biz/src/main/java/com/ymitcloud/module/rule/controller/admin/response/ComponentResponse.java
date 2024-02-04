package com.ymitcloud.module.rule.controller.admin.response;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentResponse implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 组件名称
     */
    private String componentName;
    /**
     * 组件类型 ( normal | script )
     */
    private String componentType;

    /**
     * 组件类别
     */
    private String componentCategory;
    /**
     * 组件说明
     */
    private String componentDesc;

    /**
     * 参数列表
     */
    private List<ComponentParamResponse> params;
}
