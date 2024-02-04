package com.ymitcloud.module.promotion.controller.admin.diy.vo.page;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/** 
 * 管理后台 - 装修页面属性更新 Request VO
 */

@Data
@ToString(callSuper = true)
public class DiyPagePropertyUpdateRequestVO {


    /** 
     * 装修页面编号
     */
    @NotNull(message = "装修页面编号不能为空")
    private Long id;

    /** 
     * 页面属性，JSON 格式
     */

    @NotBlank(message = "页面属性不能为空")
    private String property;

}
