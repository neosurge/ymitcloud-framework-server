package com.ymitcloud.module.bpm.controller.admin.definition.vo.form;


import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 动态表单更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmFormUpdateReqVO extends BpmFormBaseVO {

    /**
     * 表单编号
     */
    @NotNull(message = "表单编号不能为空")
    private Long id;
    /**
     * 表单的配置-JSON 字符串
     */
    @NotNull(message = "表单的配置不能为空")
    private String conf;
    /**
     * 表单项的数组-JSON 字符串的数组
     */

    @NotNull(message = "表单项的数组不能为空")
    private List<String> fields;

}
