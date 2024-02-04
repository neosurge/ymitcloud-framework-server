package com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/** 
 * 管理后台 - 砍价活动 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainActivityRespVO extends BargainActivityBaseVO {


    /** 
     * 活动编号
     */
    private Long id;

    /** 
     * 活动状态
     */
    private Integer status;

    /** 
     * 创建时间
     */

    private LocalDateTime createTime;

}
