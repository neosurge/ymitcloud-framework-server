package com.ymitcloud.module.bpm.controller.admin.definition.vo.model;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;


/**
 * 管理后台 - 流程模型的创建 Request VO
 */
@Data
public class BpmModelCreateReqVO {

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

}
