package com.ymitcloud.module.promotion.controller.admin.article;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Comparator;
import java.util.List;

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

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.article.vo.category.ArticleCategoryCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.category.ArticleCategoryPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.category.ArticleCategoryRespVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.category.ArticleCategorySimpleRespVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.category.ArticleCategoryUpdateReqVO;
import com.ymitcloud.module.promotion.convert.article.ArticleCategoryConvert;
import com.ymitcloud.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import com.ymitcloud.module.promotion.service.article.ArticleCategoryService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 文章分类
 */

@RestController
@RequestMapping("/promotion/article-category")
@Validated
public class ArticleCategoryController {

    @Resource
    private ArticleCategoryService articleCategoryService;


    /**
     * 创建文章分类
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:article-category:create')")
    public CommonResult<Long> createArticleCategory(@Valid @RequestBody ArticleCategoryCreateReqVO createReqVO) {
        return success(articleCategoryService.createArticleCategory(createReqVO));
    }


    /**
     * 更新文章分类
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:article-category:update')")
    public CommonResult<Boolean> updateArticleCategory(@Valid @RequestBody ArticleCategoryUpdateReqVO updateReqVO) {
        articleCategoryService.updateArticleCategory(updateReqVO);
        return success(true);
    }


    /**
     * 删除文章分类
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:article-category:delete')")
    public CommonResult<Boolean> deleteArticleCategory(@RequestParam("id") Long id) {
        articleCategoryService.deleteArticleCategory(id);
        return success(true);
    }


    /**
     * 获得文章分类
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:article-category:query')")
    public CommonResult<ArticleCategoryRespVO> getArticleCategory(@RequestParam("id") Long id) {
        ArticleCategoryDO category = articleCategoryService.getArticleCategory(id);
        return success(ArticleCategoryConvert.INSTANCE.convert(category));
    }


    /**
     * 获取文章分类精简信息列表，只包含被开启的文章分类，主要用于前端的下拉选项
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<ArticleCategorySimpleRespVO>> getSimpleDeptList() {
        // 获得分类列表，只要开启状态的
        List<ArticleCategoryDO> list = articleCategoryService
                .getArticleCategoryListByStatus(CommonStatusEnum.ENABLE.getStatus());

        // 降序排序后，返回给前端
        list.sort(Comparator.comparing(ArticleCategoryDO::getSort).reversed());
        return success(ArticleCategoryConvert.INSTANCE.convertList03(list));
    }


    /**
     * 获得文章分类分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('promotion:article-category:query')")
    public CommonResult<PageResult<ArticleCategoryRespVO>> getArticleCategoryPage(
            @Valid ArticleCategoryPageReqVO pageVO) {

        PageResult<ArticleCategoryDO> pageResult = articleCategoryService.getArticleCategoryPage(pageVO);
        return success(ArticleCategoryConvert.INSTANCE.convertPage(pageResult));
    }

}
