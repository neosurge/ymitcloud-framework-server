package com.ymitcloud.module.promotion.controller.admin.seckill.vo.config;


import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 管理后台 - 秒杀时段配置精简信息 Response VO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillConfigSimpleRespVO {


    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Long id;

    /**
     * 秒杀时段名称
     */
    @NotNull(message = "秒杀时段名称不能为空")
    private String name;

    /**
     * 开始时间点
     */
    private String startTime;

    /**
     * 结束时间点
     */

    private String endTime;

}
