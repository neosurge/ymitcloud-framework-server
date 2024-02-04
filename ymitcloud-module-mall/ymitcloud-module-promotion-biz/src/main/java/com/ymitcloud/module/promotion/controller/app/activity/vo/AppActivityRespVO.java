package com.ymitcloud.module.promotion.controller.app.activity.vo;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户 App - 营销活动 Response VO
 */
@Data
public class AppActivityRespVO {

    /**
     * 活动编号
     */
    private Long id;

    /**
     * 活动类型
     */
    private Integer type; // 对应 PromotionTypeEnum 枚举

    /**
     * 活动名称
     */
    private String name;

    /**
     * spu 编号
     */
    private Long spuId;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */

    private LocalDateTime endTime;

}
