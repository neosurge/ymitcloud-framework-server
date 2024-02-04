package com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity;


import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.seckill.vo.product.SeckillProductRespVO;

import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 秒杀活动的详细 Response VO
 */
@Data
@ToString(callSuper = true)
public class SeckillActivityDetailRespVO extends SeckillActivityBaseVO {

    /**
     * 秒杀活动id
     */
    private Long id;

    /**
     * 秒杀商品
     */

    private List<SeckillProductRespVO> products;

}
