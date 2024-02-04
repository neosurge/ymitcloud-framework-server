package com.ymitcloud.module.promotion.controller.admin.article.vo.article;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 文章管理更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticleUpdateReqVO extends ArticleBaseVO {


    /**
     * 文章编号
     */

    @NotNull(message = "文章编号不能为空")
    private Long id;

}
