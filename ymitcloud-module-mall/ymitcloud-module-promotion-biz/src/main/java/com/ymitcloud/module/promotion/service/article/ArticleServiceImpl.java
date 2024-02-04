package com.ymitcloud.module.promotion.service.article;

import com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticleCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticlePageReqVO;
import com.ymitcloud.module.promotion.controller.admin.article.vo.article.ArticleUpdateReqVO;
import com.ymitcloud.module.promotion.controller.app.article.vo.article.AppArticlePageReqVO;
import com.ymitcloud.module.promotion.convert.article.ArticleConvert;
import com.ymitcloud.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import com.ymitcloud.module.promotion.dal.dataobject.article.ArticleDO;
import com.ymitcloud.module.promotion.dal.mysql.article.ArticleMapper;
import com.ymitcloud.module.promotion.enums.ErrorCodeConstants;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 文章管理 Service 实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleCategoryService articleCategoryService;

    @Override
    public Long createArticle(ArticleCreateReqVO createReqVO) {
        // 校验分类存在
        validateArticleCategoryExists(createReqVO.getCategoryId());

        // 插入
        ArticleDO article = ArticleConvert.INSTANCE.convert(createReqVO);
        article.setBrowseCount(0); // 初始浏览量
        articleMapper.insert(article);
        // 返回
        return article.getId();
    }

    @Override
    public void updateArticle(ArticleUpdateReqVO updateReqVO) {
        // 校验存在
        validateArticleExists(updateReqVO.getId());
        // 校验分类存在
        validateArticleCategoryExists(updateReqVO.getCategoryId());

        // 更新
        ArticleDO updateObj = ArticleConvert.INSTANCE.convert(updateReqVO);
        articleMapper.updateById(updateObj);
    }

    @Override
    public void deleteArticle(Long id) {
        // 校验存在
        validateArticleExists(id);
        // 删除
        articleMapper.deleteById(id);
    }

    private void validateArticleExists(Long id) {
        if (articleMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ARTICLE_NOT_EXISTS);
        }
    }

    private void validateArticleCategoryExists(Long categoryId) {
        ArticleCategoryDO articleCategory = articleCategoryService.getArticleCategory(categoryId);
        if (articleCategory == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.ARTICLE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public ArticleDO getArticle(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public PageResult<ArticleDO> getArticlePage(ArticlePageReqVO pageReqVO) {
        return articleMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ArticleDO> getArticleCategoryListByRecommend(Boolean recommendHot, Boolean recommendBanner) {
        return articleMapper.selectList(recommendHot, recommendBanner);
    }

    @Override
    public PageResult<ArticleDO> getArticlePage(AppArticlePageReqVO pageReqVO) {
        return articleMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ArticleDO> getArticleByCategoryId(Long categoryId) {
        return articleMapper.selectList(ArticleDO::getCategoryId, categoryId);
    }

    @Override
    public Long getArticleCountByCategoryId(Long categoryId) {
        return articleMapper.selectCount(ArticleDO::getCategoryId, categoryId);
    }

    @Override
    public void addArticleBrowseCount(Long id) {
        // 校验文章是否存在
        validateArticleExists(id);
        // 增加浏览次数
        articleMapper.updateBrowseCount(id);
    }

}
