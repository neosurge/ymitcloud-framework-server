package com.ymitcloud.module.rule.controller.admin.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentParamResponse implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 参数名称
     */
    private String paramField;
    /**
     * 参数标签
     */
    private String paramLabel;

    /**
     * 参数说明
     */
    private String paramDesc;

    /**
     * 是否必须
     */
    private Boolean required;
}
