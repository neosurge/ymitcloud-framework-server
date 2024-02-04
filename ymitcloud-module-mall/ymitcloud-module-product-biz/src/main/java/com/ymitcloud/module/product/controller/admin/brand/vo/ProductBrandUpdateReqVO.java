package com.ymitcloud.module.product.controller.admin.brand.vo;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品品牌更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductBrandUpdateReqVO extends ProductBrandBaseVO {

    /**
     * 品牌编号
     */

    @NotNull(message = "品牌编号不能为空")
    private Long id;

}
