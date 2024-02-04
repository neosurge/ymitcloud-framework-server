package com.ymitcloud.module.promotion.controller.admin.bargain.vo.recrod;


import java.time.LocalDateTime;

import com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity.BargainActivityRespVO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 砍价记录的分页项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainRecordPageItemRespVO extends BargainRecordBaseVO {


    /** 
     * 记录编号
     */
    private Long id;

    /**
     *  创建时间
     */
    private LocalDateTime createTime;

    /**
     *  帮砍次数
     */

    private Integer helpCount;

    // ========== 用户相关 ==========


    /**
     *  用户昵称
     */
    private String nickname;

    /**
     *  用户头像
     */

    private String avatar;

    // ========== 活动相关 ==========

    private BargainActivityRespVO activity;

}
