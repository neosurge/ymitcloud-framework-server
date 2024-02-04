package com.ymitcloud.module.promotion.controller.app.article;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.app.article.vo.article.AppArticlePageReqVO;
import com.ymitcloud.module.promotion.controller.app.article.vo.article.AppArticleRespVO;
import com.ymitcloud.module.promotion.convert.article.ArticleConvert;
import com.ymitcloud.module.promotion.service.article.ArticleService;


import jakarta.annotation.Resource;

/**
 * 用户 APP - 文章
 */

@RestController
@RequestMapping("/promotion/article")
@Validated
public class AppArticleController {

    @Resource
    private ArticleService articleService;


    /**
     * 获得文章详情列表
     * 
     * @param recommendHot是否热门
     * @param recommendBanner  是否轮播图
     * @return
     */
    @RequestMapping("/list")
    public CommonResult<List<AppArticleRespVO>> getArticleList(
            @RequestParam(value = "recommendHot", required = false) Boolean recommendHot,
            @RequestParam(value = "recommendBanner", required = false) Boolean recommendBanner) {
        return success(ArticleConvert.INSTANCE
                .convertList03(articleService.getArticleCategoryListByRecommend(recommendHot, recommendBanner)));
    }

    /**
     * 获得文章详情分页
     * 
     * @param pageReqVO
     * @return
     */
    @RequestMapping("/page")

    public CommonResult<PageResult<AppArticleRespVO>> getArticlePage(AppArticlePageReqVO pageReqVO) {
        return success(ArticleConvert.INSTANCE.convertPage02(articleService.getArticlePage(pageReqVO)));
    }


    /**
     * 获得文章详情
     * 
     * @param id 文章编号
     * @return
     */
    @RequestMapping("/get")

    public CommonResult<AppArticleRespVO> getArticlePage(@RequestParam("id") Long id) {
        return success(ArticleConvert.INSTANCE.convert01(articleService.getArticle(id)));
    }


    /**
     * 增加文章浏览量
     * 
     * @param id 文章编号
     * @return
     */
    @PutMapping("/add-browse-count")

    public CommonResult<Boolean> addBrowseCount(@RequestParam("id") Long id) {
        articleService.addArticleBrowseCount(id);
        return success(true);
    }

}
