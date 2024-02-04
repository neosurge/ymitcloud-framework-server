package com.ymitcloud.module.product.controller.admin.sku.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 商品 SKU Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSkuRespVO extends ProductSkuBaseVO {

    /**
     * 主键
     */

    private Long id;

}
