package com.ymitcloud.module.product.controller.admin.category;


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


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.product.controller.admin.category.vo.ProductCategoryCreateReqVO;
import com.ymitcloud.module.product.controller.admin.category.vo.ProductCategoryListReqVO;
import com.ymitcloud.module.product.controller.admin.category.vo.ProductCategoryRespVO;
import com.ymitcloud.module.product.controller.admin.category.vo.ProductCategoryUpdateReqVO;
import com.ymitcloud.module.product.convert.category.ProductCategoryConvert;
import com.ymitcloud.module.product.dal.dataobject.category.ProductCategoryDO;
import com.ymitcloud.module.product.service.category.ProductCategoryService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商品分类
 */

@RestController
@RequestMapping("/product/category")
@Validated
public class ProductCategoryController {

    @Resource
    private ProductCategoryService categoryService;


    /**
     * 创建商品分类
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('product:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody ProductCategoryCreateReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }


    /**
     * 更新商品分类
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('product:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody ProductCategoryUpdateReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }


    /**
     * 删除商品分类
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('product:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }


    /**
     * 获得商品分类
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('product:category:query')")
    public CommonResult<ProductCategoryRespVO> getCategory(@RequestParam("id") Long id) {
        ProductCategoryDO category = categoryService.getCategory(id);
        return success(ProductCategoryConvert.INSTANCE.convert(category));
    }


    /**
     * 获得商品分类列表
     * @param treeListReqVO
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('product:category:query')")
    public CommonResult<List<ProductCategoryRespVO>> getCategoryList(@Valid ProductCategoryListReqVO treeListReqVO) {
        List<ProductCategoryDO> list = categoryService.getEnableCategoryList(treeListReqVO);
        list.sort(Comparator.comparing(ProductCategoryDO::getSort));
        return success(ProductCategoryConvert.INSTANCE.convertList(list));
    }

}
