package com.ymitcloud.module.promotion.controller.app.seckill.vo.activity;


import java.util.List;

import com.ymitcloud.module.promotion.controller.app.seckill.vo.config.AppSeckillConfigRespVO;

import lombok.Data;

/**
 * 用户 App - 当前秒杀活动
 */
@Data
public class AppSeckillActivityNowRespVO {

    /**
     * 秒杀时间段
     */
    private AppSeckillConfigRespVO config;

    /**
     * 秒杀活动数组
     */

    private List<AppSeckillActivityRespVO> activities;

}
