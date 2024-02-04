package com.ymitcloud.module.product.controller.admin.category.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品分类 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductCategoryBaseVO {
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
     * 移动端分类图
     */
    @NotBlank(message = "移动端分类图不能为空")
    private String picUrl;
    /**
     * PC 端分类图
     */
    private String bigPicUrl;
    /**
     * 分类排序
     */
    private Integer sort;
    /**
     * 开启状态
     */

    @NotNull(message = "开启状态不能为空")
    private Integer status;

}
