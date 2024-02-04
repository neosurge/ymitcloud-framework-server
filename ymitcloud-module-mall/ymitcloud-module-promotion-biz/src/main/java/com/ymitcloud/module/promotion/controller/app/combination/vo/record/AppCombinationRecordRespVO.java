package com.ymitcloud.module.promotion.controller.app.combination.vo.record;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户 App - 拼团记录
 */
@Data
public class AppCombinationRecordRespVO {

    /**
     * 拼团记录编号
     */
    private Long id;

    /**
     * 拼团活动编号
     */
    private Long activityId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 可参团人数
     */
    private Integer userSize;

    /**
     * 已参团人数
     */
    private Integer userCount;

    /**
     * 拼团状态
     */
    private Integer status;

    /**
     * 商品名字
     */
    private String spuName;

    /**
     * 商品图片
     */
    private String picUrl;

    /**
     * 拼团金额，单位：分
     */

    private Integer combinationPrice;

}
