package com.ymitcloud.module.promotion.controller.app.seckill.vo.activity;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/** 
 * 用户 App - 秒杀活动的详细 */
@Data
public class AppSeckillActivityDetailRespVO {

    /** 
     * 秒杀活动编号*/
    private Long id;

    /** 
     * 秒杀活动名称*/
    private String name;

    /** 
     * 活动状态*/
    private Integer status;

    /** 
     * 活动开始时间*/
    private LocalDateTime startTime;

    /** 
     * 活动结束时间*/
    private LocalDateTime endTime;

    /** 
     * 商品 SPU 编号*/
    private Long spuId;

    /** 
     * 总共限购数量", example = "10")
     */
    private Integer totalLimitCount;

    /** 
     * 单次限购数量
     */
    private Integer singleLimitCount;

    /**
     *  秒杀库存（剩余）*/
    private Integer stock;

    /** 秒杀库存（总计）*/
    private Integer totalStock;

    /** 商品信息数组*/
    private List<Product> products;

    /** 
     * 商品信息
     */
    @Data
    public static class Product {

        /** 商品 SKU 编号*/
        private Long skuId;

        /** 秒杀金额，单位：分*/
        private Integer seckillPrice;

        /** 秒杀限量库存*/

        private Integer stock;

    }

}
