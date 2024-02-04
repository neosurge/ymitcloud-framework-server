package com.ymitcloud.module.promotion.controller.app.diy.vo;


import lombok.Data;
import lombok.ToString;

/**
 * 用户 App - 装修页面属性
 */

@Data
@ToString(callSuper = true)
public class AppDiyPagePropertyRespVO {


    /**
     * 装修页面编号
     */
    private Long id;

    /**
     * 页面名称
     */
    private String name;

    /**
     * 页面属性
     */

    private String property;

}
