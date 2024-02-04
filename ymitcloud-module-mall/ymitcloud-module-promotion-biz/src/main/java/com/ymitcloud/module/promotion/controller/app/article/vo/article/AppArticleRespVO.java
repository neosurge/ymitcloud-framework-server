package com.ymitcloud.module.promotion.controller.app.article.vo.article;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 应用 App - 文章 Response VO
 */
@Data
public class AppArticleRespVO {

    /**
     * 文章编号
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 分类编号
     */
    private Long categoryId;

    /**
     * 图文封面
     */
    private String picUrl;

    /**
     * 文章简介
     */
    private String introduction;

    /**
     * 文章内容
     */
    private String description;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;

    /**
     * 浏览量
     */
    private Integer browseCount;

    /**
     * 关联的商品 SPU 编号
     */

    private Long spuId;

}
