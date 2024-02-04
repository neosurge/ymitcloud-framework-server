package com.ymitcloud.module.promotion.controller.admin.banner.vo;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - Banner更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BannerUpdateReqVO extends BannerBaseVO {


    /** 
     * banner 编号
     */

    @NotNull(message = "banner 编号不能为空")
    private Long id;

}
