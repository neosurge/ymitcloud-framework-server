package com.ymitcloud.module.product.controller.admin.spu.vo;


import java.util.List;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.product.controller.admin.sku.vo.ProductSkuCreateOrUpdateReqVO;
import com.ymitcloud.module.product.enums.spu.ProductSpuStatusEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品 SPU 更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSpuUpdateReqVO extends ProductSpuBaseVO {

    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空")
    private Long id;
    /**
     * 商品销量
     */
    private Integer salesCount;
    /**
     * 浏览量
     */
    private Integer browseCount;
    /**
     * 商品状态
     */

    @InEnum(ProductSpuStatusEnum.class)
    private Integer status;

    // ========== SKU 相关字段 =========

    /**
     * SKU 数组
     */

    @Valid
    private List<ProductSkuCreateOrUpdateReqVO> skus;

}
