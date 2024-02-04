package com.ymitcloud.module.product.controller.admin.spu.vo;


import java.util.List;

import com.ymitcloud.module.product.controller.admin.sku.vo.ProductSkuCreateOrUpdateReqVO;

import jakarta.validation.Valid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品 SPU 创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSpuCreateReqVO extends ProductSpuBaseVO {

    // ========== SKU 相关字段 =========


    /**
     * SKU 数组
     */

    @Valid
    private List<ProductSkuCreateOrUpdateReqVO> skus;

}
