package com.ymitcloud.module.promotion.controller.app.article;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Comparator;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.promotion.controller.app.article.vo.category.AppArticleCategoryRespVO;
import com.ymitcloud.module.promotion.convert.article.ArticleCategoryConvert;
import com.ymitcloud.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import com.ymitcloud.module.promotion.service.article.ArticleCategoryService;


import jakarta.annotation.Resource;

/**
 * 用户 APP - 文章分类
 */

@RestController
@RequestMapping("/promotion/article-category")
@Validated
public class AppArticleCategoryController {

    @Resource
    private ArticleCategoryService articleCategoryService;


    /**
     * 获得文章分类列表
     * 
     * @return
     */
    @RequestMapping("/list")
    public CommonResult<List<AppArticleCategoryRespVO>> getArticleCategoryList() {
        List<ArticleCategoryDO> categoryList = articleCategoryService
                .getArticleCategoryListByStatus(CommonStatusEnum.ENABLE.getStatus());

        categoryList.sort(Comparator.comparing(ArticleCategoryDO::getSort)); // 按 sort 降序排列
        return success(ArticleCategoryConvert.INSTANCE.convertList04(categoryList));
    }

}
