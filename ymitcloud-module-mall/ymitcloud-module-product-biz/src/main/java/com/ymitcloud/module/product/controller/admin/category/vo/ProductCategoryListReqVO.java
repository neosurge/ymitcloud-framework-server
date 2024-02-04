package com.ymitcloud.module.product.controller.admin.category.vo;


import lombok.Data;

/**
 * 管理后台 - 商品分类列表查询 Request VO
 */
@Data
public class ProductCategoryListReqVO {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 开启状态
     */
    private Integer status;
    /**
     * 父分类编号
     */

    private Long parentId;

}
