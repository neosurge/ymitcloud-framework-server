package com.ymitcloud.module.product.controller.admin.spu.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商品 SPU 精简 Response VO
 */
@Data
@ToString(callSuper = true)
public class ProductSpuSimpleRespVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格，单位使用：分
     */
    private Integer price;
    /**
     * 商品市场价，单位使用：分
     */
    private Integer marketPrice;
    /**
     * 商品成本价，单位使用：分
     */
    private Integer costPrice;
    /**
     * 商品库存
     */
    private Integer stock;

    // ========== 统计相关字段 =========
    /**
     * 商品销量
     */
    private Integer salesCount;
    /**
     * 商品虚拟销量
     */
    private Integer virtualSalesCount;
    /**
     * 商品浏览量
     */

    private Integer browseCount;

}
