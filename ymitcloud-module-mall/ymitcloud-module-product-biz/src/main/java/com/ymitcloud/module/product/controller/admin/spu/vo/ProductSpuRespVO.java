package com.ymitcloud.module.product.controller.admin.spu.vo;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品 SPU Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSpuRespVO extends ProductSpuBaseVO {

    /**
     * 商品 SPU 编号
     */
    private Long id;
    /**
     * 商品价格
     */
    private Integer price;
    /**
     * 商品销量
     */
    private Integer salesCount;
    /**
     * 市场价，单位使用：分
     */
    private Integer marketPrice;
    /**
     * 成本价，单位使用：分
     */
    private Integer costPrice;
    /**
     * 商品库存
     */
    private Integer stock;
    /**
     * 商品创建时间
     */
    private LocalDateTime createTime;
    /**
     * 商品状态
     */
    private Integer status;
    /**
     * 浏览量
     */

    private Integer browseCount;

}
