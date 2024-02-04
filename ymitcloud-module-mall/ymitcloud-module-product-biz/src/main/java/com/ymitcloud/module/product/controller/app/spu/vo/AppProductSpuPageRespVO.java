package com.ymitcloud.module.product.controller.app.spu.vo;
import java.util.List;

import lombok.Data;

/**
 * 用户 App - 商品 SPU Response VO
 */
@Data
public class AppProductSpuPageRespVO {
    /**
     * 商品 SPU 编号
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品简介
     */
    private String introduction;
    /**
     * 分类编号
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
     * 单位名
     */
    private String unitName;

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

    // ========== 营销相关字段 =========
    /**
     * 活动排序数组
     */
    private List<Integer> activityOrders;

    // ========== 统计相关字段 =========
    /**
     * 商品销量
     */

    private Integer salesCount;

}
