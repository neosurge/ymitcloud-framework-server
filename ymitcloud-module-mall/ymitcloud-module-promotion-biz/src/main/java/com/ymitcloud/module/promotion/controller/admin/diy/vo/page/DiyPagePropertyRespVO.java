package com.ymitcloud.module.promotion.controller.admin.diy.vo.page;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 装修页面属性 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyPagePropertyRespVO extends DiyPageBaseVO {


    /** 
     * 装修页面编号
     */
    private Long id;

    /** 
     * 页面属性
     */

    private String property;

}
