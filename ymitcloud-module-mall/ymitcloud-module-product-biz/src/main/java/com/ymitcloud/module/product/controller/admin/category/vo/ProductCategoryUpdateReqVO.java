package com.ymitcloud.module.product.controller.admin.category.vo;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品分类更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCategoryUpdateReqVO extends ProductCategoryBaseVO {


    /**
     * 分类编号
     */
    @NotNull(message = "分类编号不能为空")
    private Long id;

    /**
     * 分类描述
     */

    private String description;

}
