package com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.seckill.vo.product.SeckillProductRespVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 秒杀活动 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SeckillActivityRespVO extends SeckillActivityBaseVO {


    /**
     * 秒杀活动 id
     */
    private Long id;

    /**
     * 秒杀商品
     */
    private List<SeckillProductRespVO> products;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 订单实付金额，单位：分
     */
    private Integer totalPrice;

    /**
     * 秒杀库存
     */
    private Integer stock;

    /**
     * 秒杀总库存
     */
    private Integer totalStock;

    /**
     * 新增订单数
     */
    private Integer orderCount;

    /**
     * 付款人数
     */
    private Integer userCount;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    // ========== 商品字段 ==========


    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 商品主图
     */
    private String picUrl;
    /**
     * 商品市场价，单位：分
     */

    private Integer marketPrice;

}
