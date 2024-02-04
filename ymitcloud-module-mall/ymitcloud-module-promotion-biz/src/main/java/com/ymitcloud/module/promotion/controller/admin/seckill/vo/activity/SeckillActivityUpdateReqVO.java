package com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity;


import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.seckill.vo.product.SeckillProductBaseVO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 秒杀活动更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SeckillActivityUpdateReqVO extends SeckillActivityBaseVO {


    /**
     * 秒杀活动id
     */
    private Long id;

    /**
     * 秒杀商品
     */

    private List<SeckillProductBaseVO> products;

}
