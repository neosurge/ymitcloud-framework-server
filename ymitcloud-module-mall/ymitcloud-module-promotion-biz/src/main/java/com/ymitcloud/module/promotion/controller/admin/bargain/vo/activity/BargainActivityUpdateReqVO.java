package com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 砍价活动更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainActivityUpdateReqVO extends BargainActivityBaseVO {


    /** 
     * 活动编号
     */

    @NotNull(message = "活动编号不能为空")
    private Long id;

}
