package com.ymitcloud.module.product.controller.admin.brand;


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
import com.ymitcloud.module.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import com.ymitcloud.module.product.controller.admin.brand.vo.ProductBrandListReqVO;
import com.ymitcloud.module.product.controller.admin.brand.vo.ProductBrandPageReqVO;
import com.ymitcloud.module.product.controller.admin.brand.vo.ProductBrandRespVO;
import com.ymitcloud.module.product.controller.admin.brand.vo.ProductBrandSimpleRespVO;
import com.ymitcloud.module.product.controller.admin.brand.vo.ProductBrandUpdateReqVO;
import com.ymitcloud.module.product.convert.brand.ProductBrandConvert;
import com.ymitcloud.module.product.dal.dataobject.brand.ProductBrandDO;
import com.ymitcloud.module.product.service.brand.ProductBrandService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商品品牌
 */

@RestController
@RequestMapping("/product/brand")
@Validated
public class ProductBrandController {

    @Resource
    private ProductBrandService brandService;


    /**
     * 创建品牌
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('product:brand:create')")
    public CommonResult<Long> createBrand(@Valid @RequestBody ProductBrandCreateReqVO createReqVO) {
        return success(brandService.createBrand(createReqVO));
    }


    /**
     * 更新品牌
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('product:brand:update')")
    public CommonResult<Boolean> updateBrand(@Valid @RequestBody ProductBrandUpdateReqVO updateReqVO) {
        brandService.updateBrand(updateReqVO);
        return success(true);
    }


    /**
     * 删除品牌
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('product:brand:delete')")
    public CommonResult<Boolean> deleteBrand(@RequestParam("id") Long id) {
        brandService.deleteBrand(id);
        return success(true);
    }


    /**
     * 获得品牌
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('product:brand:query')")
    public CommonResult<ProductBrandRespVO> getBrand(@RequestParam("id") Long id) {
        ProductBrandDO brand = brandService.getBrand(id);
        return success(ProductBrandConvert.INSTANCE.convert(brand));
    }


    /**
     * 获取品牌精简信息列表,主要用于前端的下拉选项
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    public CommonResult<List<ProductBrandSimpleRespVO>> getSimpleBrandList() {
        // 获取品牌列表，只要开启状态的
        List<ProductBrandDO> list = brandService.getBrandListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 排序后，返回给前端
        return success(ProductBrandConvert.INSTANCE.convertList1(list));
    }


    /**
     * 获得品牌分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('product:brand:query')")
    public CommonResult<PageResult<ProductBrandRespVO>> getBrandPage(@Valid ProductBrandPageReqVO pageVO) {
        PageResult<ProductBrandDO> pageResult = brandService.getBrandPage(pageVO);
        return success(ProductBrandConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获得品牌列表
     * @param listVO
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('product:brand:query')")
    public CommonResult<List<ProductBrandRespVO>> getBrandList(@Valid ProductBrandListReqVO listVO) {
        List<ProductBrandDO> list = brandService.getBrandList(listVO);
        list.sort(Comparator.comparing(ProductBrandDO::getSort));
        return success(ProductBrandConvert.INSTANCE.convertList(list));
    }

}
