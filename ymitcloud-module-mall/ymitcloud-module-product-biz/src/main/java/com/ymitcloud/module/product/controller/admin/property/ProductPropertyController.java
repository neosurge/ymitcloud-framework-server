package com.ymitcloud.module.product.controller.admin.property;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.Collections;
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
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.controller.admin.property.vo.property.ProductPropertyAndValueRespVO;
import com.ymitcloud.module.product.controller.admin.property.vo.property.ProductPropertyCreateReqVO;
import com.ymitcloud.module.product.controller.admin.property.vo.property.ProductPropertyListReqVO;
import com.ymitcloud.module.product.controller.admin.property.vo.property.ProductPropertyPageReqVO;
import com.ymitcloud.module.product.controller.admin.property.vo.property.ProductPropertyRespVO;
import com.ymitcloud.module.product.controller.admin.property.vo.property.ProductPropertyUpdateReqVO;

import com.ymitcloud.module.product.convert.property.ProductPropertyConvert;
import com.ymitcloud.module.product.dal.dataobject.property.ProductPropertyDO;
import com.ymitcloud.module.product.dal.dataobject.property.ProductPropertyValueDO;
import com.ymitcloud.module.product.service.property.ProductPropertyService;
import com.ymitcloud.module.product.service.property.ProductPropertyValueService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商品属性项
 */

@RestController
@RequestMapping("/product/property")
@Validated
public class ProductPropertyController {

    @Resource
    private ProductPropertyService productPropertyService;
    @Resource
    private ProductPropertyValueService productPropertyValueService;


    /**
     * 创建属性项
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('product:property:create')")
    public CommonResult<Long> createProperty(@Valid @RequestBody ProductPropertyCreateReqVO createReqVO) {
        return success(productPropertyService.createProperty(createReqVO));
    }


    /**
     * 更新属性项
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('product:property:update')")
    public CommonResult<Boolean> updateProperty(@Valid @RequestBody ProductPropertyUpdateReqVO updateReqVO) {
        productPropertyService.updateProperty(updateReqVO);
        return success(true);
    }


    /**
     * 删除属性项
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('product:property:delete')")
    public CommonResult<Boolean> deleteProperty(@RequestParam("id") Long id) {
        productPropertyService.deleteProperty(id);
        return success(true);
    }


    /**
     * 获得属性项
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('product:property:query')")
    public CommonResult<ProductPropertyRespVO> getProperty(@RequestParam("id") Long id) {
        return success(ProductPropertyConvert.INSTANCE.convert(productPropertyService.getProperty(id)));
    }


    /**
     * 获得属性项列表
     * 
     * @param listReqVO
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('product:property:query')")
    public CommonResult<List<ProductPropertyRespVO>> getPropertyList(@Valid ProductPropertyListReqVO listReqVO) {
        return success(ProductPropertyConvert.INSTANCE.convertList(productPropertyService.getPropertyList(listReqVO)));
    }


    /**
     * 获得属性项分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('product:property:query')")
    public CommonResult<PageResult<ProductPropertyRespVO>> getPropertyPage(@Valid ProductPropertyPageReqVO pageVO) {
        return success(ProductPropertyConvert.INSTANCE.convertPage(productPropertyService.getPropertyPage(pageVO)));
    }


    /**
     * 获得属性项列表
     * 
     * @param listReqVO
     * @return
     */
    @PostMapping("/get-value-list")

    @PreAuthorize("@ss.hasPermission('product:property:query')")
    public CommonResult<List<ProductPropertyAndValueRespVO>> getPropertyAndValueList(
            @Valid @RequestBody ProductPropertyListReqVO listReqVO) {
        // 查询属性项
        List<ProductPropertyDO> keys = productPropertyService.getPropertyList(listReqVO);
        if (CollUtil.isEmpty(keys)) {
            return success(Collections.emptyList());
        }
        // 查询属性值

        List<ProductPropertyValueDO> values = productPropertyValueService
                .getPropertyValueListByPropertyId(convertSet(keys, ProductPropertyDO::getId));

        return success(ProductPropertyConvert.INSTANCE.convertList(keys, values));
    }

}
