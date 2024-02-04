package com.ymitcloud.module.promotion.controller.admin.article.vo.category;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 文章分类 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ArticleCategoryBaseVO {


    /**
     * 文章分类名称
     */
    @NotNull(message = "文章分类名称不能为空")
    private String name;

    /**
     * 图标地址
     */
    private String picUrl;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 排序
     */

    @NotNull(message = "排序不能为空")
    private Integer sort;

}
