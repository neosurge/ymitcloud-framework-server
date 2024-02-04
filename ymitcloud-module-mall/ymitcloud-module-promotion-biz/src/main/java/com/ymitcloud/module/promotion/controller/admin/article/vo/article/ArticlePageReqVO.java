package com.ymitcloud.module.promotion.controller.admin.article.vo.article;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 管理后台 - 文章管理分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticlePageReqVO extends PageParam {


    /**
     * 文章分类编号
     */
    private Long categoryId;

    /**
     * 关联商品编号
     */
    private Long spuId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否热门(小程序)
     */
    private Boolean recommendHot;

    /**
     * 是否轮播图(小程序)
     */
    private Boolean recommendBanner;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
