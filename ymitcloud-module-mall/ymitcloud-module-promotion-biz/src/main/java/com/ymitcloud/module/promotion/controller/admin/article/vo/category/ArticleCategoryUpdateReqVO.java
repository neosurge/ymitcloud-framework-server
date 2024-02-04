package com.ymitcloud.module.promotion.controller.admin.article.vo.category;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 文章分类更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticleCategoryUpdateReqVO extends ArticleCategoryBaseVO {

    /**
     * 文章分类编号
     */

    @NotNull(message = "文章分类编号不能为空")
    private Long id;

}
