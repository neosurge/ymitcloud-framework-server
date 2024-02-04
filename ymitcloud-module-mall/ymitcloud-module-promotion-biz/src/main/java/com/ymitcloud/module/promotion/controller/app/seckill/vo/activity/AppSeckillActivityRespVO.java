package com.ymitcloud.module.promotion.controller.app.seckill.vo.activity;


import lombok.Data;

/**
 * 用户 App - 秒杀活动
 */
@Data
public class AppSeckillActivityRespVO {

    /**
     * 秒杀活动编号
     */
    private Long id;

    /**
     * 秒杀活动名称
     */
    private String name;

    /**
     * 商品 SPU 编号
     */
    private Long spuId;

    /**
     * 商品图片
     */
    private String picUrl;
    /**
     * 单位名
     */
    private String unitName;
    /**
     * 商品市场价，单位：分
     */
    private Integer marketPrice;

    /**
     * 秒杀活动状态
     */
    private Integer status;

    /**
     * 秒杀库存（剩余）
     */
    private Integer stock;
    /**
     * 秒杀库存（总共）
     */
    private Integer totalStock;

    /**
     * 秒杀金额，单位：分
     */

    private Integer seckillPrice;

}
