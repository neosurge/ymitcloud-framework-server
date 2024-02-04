package com.ymitcloud.module.promotion.controller.admin.diy.vo.template;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/** 
 * 管理后台 - 装修模板属性更新 Request VO
 */

@Data
@ToString(callSuper = true)
public class DiyTemplatePropertyUpdateRequestVO {


    /** 
     * 装修模板编号
     */
    @NotNull(message = "装修模板编号不能为空")
    private Long id;

    /** 
     * 模板属性，JSON 格式
     */

    @NotBlank(message = "模板属性不能为空")
    private String property;

}
