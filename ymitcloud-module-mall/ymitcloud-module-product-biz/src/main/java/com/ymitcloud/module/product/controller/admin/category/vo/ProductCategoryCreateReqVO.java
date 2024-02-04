package com.ymitcloud.module.product.controller.admin.category.vo;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品分类创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCategoryCreateReqVO extends ProductCategoryBaseVO {

    /**
     * 分类描述
     */

    private String description;

}
