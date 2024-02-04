package com.ymitcloud.module.rule.controller.admin.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentParamSaveRequest implements Serializable {
    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空")
    private String paramField;
    /**
     * 参数标签
     */
    @NotBlank(message = "参数标签不能为空")
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
