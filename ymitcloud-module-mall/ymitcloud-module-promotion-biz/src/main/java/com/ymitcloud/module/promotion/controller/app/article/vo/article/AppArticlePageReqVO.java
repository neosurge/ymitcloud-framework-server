package com.ymitcloud.module.promotion.controller.app.article.vo.article;

import com.ymitcloud.framework.common.pojo.PageParam;


import lombok.Data;

/**
 * 应用 App - 文章的分页 Request VO
 */
@Data
public class AppArticlePageReqVO extends PageParam {

    /**
     * 分类编号
     */

    private Long categoryId;

}
