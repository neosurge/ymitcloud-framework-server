package com.ymitcloud.module.promotion.controller.admin.banner.vo;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

/** 
 * 管理后台 - Banner Response VO
 */

@Data
@ToString(callSuper = true)
public class BannerRespVO  extends BannerBaseVO {


    /** 
     * 编号
     */
    private Long id;

    /** 
     * 创建时间
     */

    private LocalDateTime createTime;

}
