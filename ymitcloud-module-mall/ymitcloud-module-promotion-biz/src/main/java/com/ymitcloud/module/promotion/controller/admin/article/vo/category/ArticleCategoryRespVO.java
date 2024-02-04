package com.ymitcloud.module.promotion.controller.admin.article.vo.category;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 文章分类 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticleCategoryRespVO extends ArticleCategoryBaseVO {

    /**
     * 文章分类编号
     */
    private Long id;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
