package com.ymitcloud.module.promotion.controller.app.combination.vo.activity;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 用户 App - 拼团活动明细
 */
@Data
public class AppCombinationActivityDetailRespVO {

    /**
     * 拼团活动编号
     */
    private Long id;

    /**
     * 拼团活动名称
     */
    private String name;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 拼团人数
     */
    private Integer userSize;

    /**
     * 成功的拼团数量
     */
    private Integer successCount;

    /**
     * 商品 SPU 编号
     */
    private Long spuId;

    /**
     * 总共限购数量
     */
    private Integer totalLimitCount;

    /**
     * 单次限购数量
     */
    private Integer singleLimitCount;

    /**
     * 商品信息数组
     */
    private List<Product> products;

    /**
     * 商品信息
     */
    @Data
    public static class Product {

        /**
         * 商品 SKU 编号
         */
        private Long skuId;

        /**
         * 拼团金额，单位：分
         */

        private Integer combinationPrice;

    }

}
