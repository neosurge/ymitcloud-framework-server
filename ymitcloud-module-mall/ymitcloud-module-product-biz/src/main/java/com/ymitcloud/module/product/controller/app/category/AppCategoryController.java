package com.ymitcloud.module.product.controller.app.category;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.Comparator;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.product.controller.app.category.vo.AppCategoryRespVO;
import com.ymitcloud.module.product.convert.category.ProductCategoryConvert;
import com.ymitcloud.module.product.dal.dataobject.category.ProductCategoryDO;
import com.ymitcloud.module.product.service.category.ProductCategoryService;


import jakarta.annotation.Resource;

/**
 * 用户 APP - 商品分类
 */

@RestController
@RequestMapping("/product/category")
@Validated
public class AppCategoryController {

    @Resource
    private ProductCategoryService categoryService;


    /**
     * 获得商品分类列表
     * 
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppCategoryRespVO>> getProductCategoryList() {
        List<ProductCategoryDO> list = categoryService.getEnableCategoryList();
        list.sort(Comparator.comparing(ProductCategoryDO::getSort));
        return success(ProductCategoryConvert.INSTANCE.convertList03(list));
    }

}
