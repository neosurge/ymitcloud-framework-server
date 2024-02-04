package com.ymitcloud.module.promotion.controller.admin.article.vo.article;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 文章管理 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticleRespVO extends ArticleBaseVO {


    /**
     * 文章编号
     */
    private Long id;

    /**
     * 浏览量
     */
    private Integer browseCount;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
