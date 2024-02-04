package com.ymitcloud.module.product.controller.admin.brand.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品品牌 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductBrandBaseVO {

    /**
     * 品牌名称
     */
    @NotNull(message = "品牌名称不能为空")
    private String name;
    /**
     * 品牌图片
     */
    @NotNull(message = "品牌图片不能为空")
    private String picUrl;
    /**
     * 品牌排序
     */
    @NotNull(message = "品牌排序不能为空")
    private Integer sort;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 状态
     */

    @NotNull(message = "状态不能为空")
    private Integer status;

}
