package com.ymitcloud.module.promotion.controller.admin.article.vo.article;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 文章管理 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class ArticleBaseVO {


    /**
     * 文章分类编号
     */
    @NotNull(message = "文章分类编号不能为空")
    private Long categoryId;

    /**
     * 关联商品编号
     */
    @NotNull(message = "关联商品不能为空")
    private Long spuId;

    /**
     * 文章标题
     */
    @NotNull(message = "文章标题不能为空")
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章封面图片地址
     */
    @NotNull(message = "文章封面图片地址不能为空")
    private String picUrl;

    /**
     * 文章简介
     */
    private String introduction;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 是否热门(小程序)
     */
    @NotNull(message = "是否热门(小程序)不能为空")
    private Boolean recommendHot;

    /**
     * 是否轮播图(小程序)
     */
    @NotNull(message = "是否轮播图(小程序)不能为空")
    private Boolean recommendBanner;

    /**
     * 文章内容
     */

    @NotNull(message = "文章内容不能为空")
    private String content;

}
