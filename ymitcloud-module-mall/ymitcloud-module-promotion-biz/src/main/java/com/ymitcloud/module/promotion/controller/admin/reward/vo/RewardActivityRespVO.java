package com.ymitcloud.module.promotion.controller.admin.reward.vo;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 满减送活动 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RewardActivityRespVO extends RewardActivityBaseVO {


    /**
     * 活动编号
     */
    private Integer id;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
