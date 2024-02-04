package com.ymitcloud.module.promotion.controller.admin.article.vo.category;


import lombok.Data;
/**
 * 管理后台 - 文章分类精简信息 Response VO
 */
@Data
public class ArticleCategorySimpleRespVO {
    /**
     * 文章分类编号
     */
    private Long id;
    /**
     * 文章分类名称
     */

    private String name;

}
