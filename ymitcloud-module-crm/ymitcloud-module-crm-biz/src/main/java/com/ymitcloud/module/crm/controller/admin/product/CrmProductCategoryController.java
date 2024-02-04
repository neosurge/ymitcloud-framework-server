package com.ymitcloud.module.crm.controller.admin.product;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

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
import com.ymitcloud.framework.common.util.object.BeanUtils;
import com.ymitcloud.module.crm.controller.admin.product.vo.category.CrmProductCategoryCreateReqVO;
import com.ymitcloud.module.crm.controller.admin.product.vo.category.CrmProductCategoryListReqVO;
import com.ymitcloud.module.crm.controller.admin.product.vo.category.CrmProductCategoryRespVO;
import com.ymitcloud.module.crm.dal.dataobject.product.CrmProductCategoryDO;
import com.ymitcloud.module.crm.service.product.CrmProductCategoryService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 产品分类
 */

@RestController
@RequestMapping("/crm/product-category")
@Validated
public class CrmProductCategoryController {

    @Resource
    private CrmProductCategoryService productCategoryService;


    /**
     * 创建产品分类
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:product-category:create')")
    public CommonResult<Long> createProductCategory(@Valid @RequestBody CrmProductCategoryCreateReqVO createReqVO) {
        return success(productCategoryService.createProductCategory(createReqVO));
    }


    /**
     * 更新产品分类
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:product-category:update')")
    public CommonResult<Boolean> updateProductCategory(@Valid @RequestBody CrmProductCategoryCreateReqVO updateReqVO) {
        productCategoryService.updateProductCategory(updateReqVO);
        return success(true);
    }


    /**
     * 删除产品分类
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:product-category:delete')")
    public CommonResult<Boolean> deleteProductCategory(@RequestParam("id") Long id) {
        productCategoryService.deleteProductCategory(id);
        return success(true);
    }


    /**
     * 获得产品分类
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:product-category:query')")
    public CommonResult<CrmProductCategoryRespVO> getProductCategory(@RequestParam("id") Long id) {
        CrmProductCategoryDO category = productCategoryService.getProductCategory(id);
        return success(BeanUtils.toBean(category, CrmProductCategoryRespVO.class));
    }


    /**
     * 获得产品分类列表
     * 
     * @param listReqVO
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('crm:product-category:query')")
    public CommonResult<List<CrmProductCategoryRespVO>> getProductCategoryList(
            @Valid CrmProductCategoryListReqVO listReqVO) {

        List<CrmProductCategoryDO> list = productCategoryService.getProductCategoryList(listReqVO);
        return success(BeanUtils.toBean(list, CrmProductCategoryRespVO.class));
    }

}
