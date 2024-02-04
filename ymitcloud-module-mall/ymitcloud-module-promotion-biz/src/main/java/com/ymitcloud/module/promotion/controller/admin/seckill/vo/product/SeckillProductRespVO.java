package com.ymitcloud.module.promotion.controller.admin.seckill.vo.product;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 秒杀参与商品 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SeckillProductRespVO extends SeckillProductBaseVO {


    /**
     * 秒杀参与商品编号
     */
    private Long id;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
