package com.ymitcloud.module.promotion.controller.app.seckill.vo.config;


import java.util.List;

import lombok.Data;

/**
 * 用户 App - 秒杀时间段
 */
@Data
public class AppSeckillConfigRespVO {

    /**
     * 秒杀时间段编号
     */
    private Long id;

    /**
     * 开始时间点
     */
    private String startTime;
    /**
     * 结束时间点
     */
    private String endTime;

    /**
     * 轮播图
     */

    private List<String> sliderPicUrls;

}
