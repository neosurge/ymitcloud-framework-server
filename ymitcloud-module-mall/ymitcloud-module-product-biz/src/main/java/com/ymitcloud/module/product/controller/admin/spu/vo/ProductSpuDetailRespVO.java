package com.ymitcloud.module.product.controller.admin.spu.vo;


import java.util.List;

import com.ymitcloud.module.product.controller.admin.sku.vo.ProductSkuRespVO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品 SPU 详细 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSpuDetailRespVO extends ProductSpuRespVO {

    // ========== SKU 相关字段 =========


    /**
     * SKU 数组
     */

    private List<ProductSkuRespVO> skus;

}
