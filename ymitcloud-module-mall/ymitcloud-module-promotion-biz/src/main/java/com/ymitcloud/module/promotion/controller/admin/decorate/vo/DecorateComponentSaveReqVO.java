package com.ymitcloud.module.promotion.controller.admin.decorate.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.decorate.DecoratePageEnum;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 页面装修的保存 Request VO
 */
@Data
public class DecorateComponentSaveReqVO {

    /**
     * 页面 id
     */

    @NotNull(message = "页面 id 不能为空")
    @InEnum(DecoratePageEnum.class)
    private Integer page;


    /**
     * 组件编码
     */
    @NotEmpty(message = "组件编码不能为空")
    private String code;

    /**
     * 组件对应值, json 字符串, 含内容配置，具体数据
     */
    @NotEmpty(message = "组件值为空")
    private String value;

    /**
     * 状态
     */

    private Integer status;

}
