package com.ymitcloud.module.promotion.controller.admin.seckill.vo.config;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 秒杀时段 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SeckillConfigRespVO extends SeckillConfigBaseVO {


    /**
     * 编号
     */
    private Long id;

    /**
     * 秒杀活动数量
     */
    private Integer seckillActivityCount;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
