package com.ymitcloud.module.product.controller.app.category.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 APP - 商品分类 Response VO
 */
@Data
public class AppCategoryRespVO {
    /**
     * 分类编号
     */
    private Long id;
    /**
     * 父分类编号
     */
    @NotNull(message = "父分类编号不能为空")
    private Long parentId;
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;
    /**
     * 分类图片
     */

    @NotBlank(message = "分类图片不能为空")
    private String picUrl;

}
