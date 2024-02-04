package com.ymitcloud.module.product.controller.app.spu.vo;


import java.util.List;

import com.ymitcloud.module.product.controller.app.property.vo.value.AppProductPropertyValueDetailRespVO;

import lombok.Data;

/**
 * 用户 App - 商品 SPU 明细 Response VO
 */
@Data
public class AppProductSpuDetailRespVO {

    /**
     * 商品 SPU 编号
     */

    private Long id;

    // ========== 基本信息 =========


    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品简介
     */
    private String introduction;
    /**
     * 商品详情
     */
    private String description;
    /**
     * 商品分类编号
     */
    private Long categoryId;

    /**
     * 商品封面图
     */
    private String picUrl;

    /**
     * 商品轮播图
     */
    private List<String> sliderPicUrls;

    /**
     * 商品视频
     */
    private String videoUrl;

    /**
     * 单位名
     */

    private String unitName;

    // ========== 营销相关字段 =========


    /**
     * 活动排序数组
     */

    private List<Integer> activityOrders;

    // ========== SKU 相关字段 =========


    /**
     * 规格类型
     */
    private Boolean specType;

    /**
     * 商品价格，单位使用：分
     */
    private Integer price;

    /**
     * 市场价，单位使用：分
     */
    private Integer marketPrice;

    /**
     * VIP 价格，单位使用：分
     */
    private Integer vipPrice;

    /**
     * 库存
     */

    private Integer stock;

    /**
     * SKU 数组
     */
    private List<Sku> skus;

    // ========== 统计相关字段 =========


    /**
     * 商品销量
     */
    private Integer salesCount;

    /**
     * 用户 App - 商品 SPU 明细的 SKU 信息
     */
    @Data
    public static class Sku {

        /**
         * 商品 SKU 编号
         */

        private Long id;

        /**
         * 商品属性数组
         */
        private List<AppProductPropertyValueDetailRespVO> properties;


        /**
         * 销售价格，单位：分
         */
        private Integer price;

        /**
         * 市场价，单位使用：分
         */
        private Integer marketPrice;

        /**
         * VIP 价格，单位使用：分
         */
        private Integer vipPrice;
        /**
         * 图片地址
         */
        private String picUrl;
        /**
         * 库存
         */
        private Integer stock;
        /**
         * 商品重量
         */
        private Double weight;
        /**
         * 商品体积
         */

        private Double volume;

    }

}
