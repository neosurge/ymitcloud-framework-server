package com.ymitcloud.module.promotion.controller.app.diy.vo;

import com.fasterxml.jackson.annotation.JsonRawValue;


import lombok.Data;
import lombok.ToString;

/**
 * 用户 App - 装修模板属性
 */

@Data
@ToString(callSuper = true)
public class AppDiyTemplatePropertyRespVO {


    /**
     * 装修模板编号
     */
    private Long id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板属性
     */
    @JsonRawValue
    private String property;

    /**
     * 首页
     */
    @JsonRawValue
    private String home;

    /**
     * 我的
     */

    @JsonRawValue
    private String user;

}
