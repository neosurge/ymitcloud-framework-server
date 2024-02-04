package com.ymitcloud.module.promotion.controller.admin.article;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticleCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticlePageReqVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticleRespVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticleUpdateReqVO;
import com.ymitcloud.module.promotion.convert.article.ArticleConvert;
import com.ymitcloud.module.promotion.dal.dataobject.article.ArticleDO;
import com.ymitcloud.module.promotion.service.article.ArticleService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 文章管理
 */

@RestController
@RequestMapping("/promotion/article")
@Validated
public class ArticleController {

    @Resource
    private ArticleService articleService;


    /**
     * 创建文章管理
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:article:create')")
    public CommonResult<Long> createArticle(@Valid @RequestBody ArticleCreateReqVO createReqVO) {
        return success(articleService.createArticle(createReqVO));
    }


    /**
     * 更新文章管理
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:article:update')")
    public CommonResult<Boolean> updateArticle(@Valid @RequestBody ArticleUpdateReqVO updateReqVO) {
        articleService.updateArticle(updateReqVO);
        return success(true);
    }


    /**
     * 删除文章管理
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:article:delete')")
    public CommonResult<Boolean> deleteArticle(@RequestParam("id") Long id) {
        articleService.deleteArticle(id);
        return success(true);
    }


    /**
     * 获得文章管理
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:article:query')")
    public CommonResult<ArticleRespVO> getArticle(@RequestParam("id") Long id) {
        ArticleDO article = articleService.getArticle(id);
        return success(ArticleConvert.INSTANCE.convert(article));
    }


    /**
     * 获得文章管理分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:article:query')")
    public CommonResult<PageResult<ArticleRespVO>> getArticlePage(@Valid ArticlePageReqVO pageVO) {
        PageResult<ArticleDO> pageResult = articleService.getArticlePage(pageVO);
        return success(ArticleConvert.INSTANCE.convertPage(pageResult));
    }

}
