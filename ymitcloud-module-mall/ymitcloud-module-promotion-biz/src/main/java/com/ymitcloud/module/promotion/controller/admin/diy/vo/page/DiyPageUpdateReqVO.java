package com.ymitcloud.module.promotion.controller.admin.diy.vo.page;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 装修页面更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyPageUpdateReqVO extends DiyPageBaseVO {


    /** 
     * 装修页面编号
     */

    @NotNull(message = "装修页面编号不能为空")
    private Long id;

}
