package com.ymitcloud.module.promotion.controller.admin.decorate.vo;


import lombok.Data;

/** 
 * 管理后台 - 页面装修 Resp VO
 */
@Data
public class DecorateComponentRespVO {

    /**
     *  组件编码
     */
    private String code;

    /** 
     * 组件的内容配置项
     */
    private String value;

    /** 
     * 状态
     */

    private Integer status;

}
