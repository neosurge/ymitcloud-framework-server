package com.ymitcloud.module.product.controller.admin.property;


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
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValueCreateReqVO;
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValuePageReqVO;
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValueRespVO;
import com.ymitcloud.module.product.controller.admin.property.vo.value.ProductPropertyValueUpdateReqVO;
import com.ymitcloud.module.product.convert.property.ProductPropertyValueConvert;
import com.ymitcloud.module.product.service.property.ProductPropertyValueService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 商品属性值
 */

@RestController
@RequestMapping("/product/property/value")
@Validated
public class ProductPropertyValueController {

    @Resource
    private ProductPropertyValueService productPropertyValueService;


    /**
     * 创建属性值
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('product:property:create')")
    public CommonResult<Long> createPropertyValue(@Valid @RequestBody ProductPropertyValueCreateReqVO createReqVO) {
        return success(productPropertyValueService.createPropertyValue(createReqVO));
    }


    /**
     * 更新属性值
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('product:property:update')")
    public CommonResult<Boolean> updatePropertyValue(@Valid @RequestBody ProductPropertyValueUpdateReqVO updateReqVO) {
        productPropertyValueService.updatePropertyValue(updateReqVO);
        return success(true);
    }


    /**
     * 删除属性值
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('product:property:delete')")
    public CommonResult<Boolean> deletePropertyValue(@RequestParam("id") Long id) {
        productPropertyValueService.deletePropertyValue(id);
        return success(true);
    }


    /**
     * 获得属性值
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('product:property:query')")
    public CommonResult<ProductPropertyValueRespVO> getPropertyValue(@RequestParam("id") Long id) {
        return success(ProductPropertyValueConvert.INSTANCE.convert(productPropertyValueService.getPropertyValue(id)));
    }


    /**
     * 获得属性值分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('product:property:query')")
    public CommonResult<PageResult<ProductPropertyValueRespVO>> getPropertyValuePage(
            @Valid ProductPropertyValuePageReqVO pageVO) {
        return success(ProductPropertyValueConvert.INSTANCE
                .convertPage(productPropertyValueService.getPropertyValuePage(pageVO)));

    }
}
