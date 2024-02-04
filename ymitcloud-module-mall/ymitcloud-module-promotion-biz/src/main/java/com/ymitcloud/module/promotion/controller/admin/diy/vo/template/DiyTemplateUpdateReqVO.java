package com.ymitcloud.module.promotion.controller.admin.diy.vo.template;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 装修模板更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyTemplateUpdateReqVO extends DiyTemplateBaseVO {


    /** 
     * 装修模板编号
     */

    @NotNull(message = "装修模板编号不能为空")
    private Long id;

}
