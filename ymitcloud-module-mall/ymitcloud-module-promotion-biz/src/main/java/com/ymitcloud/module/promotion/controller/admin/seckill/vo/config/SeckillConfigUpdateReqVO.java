package com.ymitcloud.module.promotion.controller.admin.seckill.vo.config;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 秒杀时段更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SeckillConfigUpdateReqVO extends SeckillConfigBaseVO {


    /**
     * 编号
     */

    @NotNull(message = "编号不能为空")
    private Long id;

}
