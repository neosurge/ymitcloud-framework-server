package com.ymitcloud.module.promotion.controller.app.article.vo.category;


import lombok.Data;

/**
 * 应用 App - 文章分类 Response VO
 */
@Data
public class AppArticleCategoryRespVO {

    /**
     * 分类编号
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图标
     */

    private String picUrl;

}
