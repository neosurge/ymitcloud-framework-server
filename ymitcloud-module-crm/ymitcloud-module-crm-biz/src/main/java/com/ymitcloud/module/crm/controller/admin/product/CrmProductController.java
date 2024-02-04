package com.ymitcloud.module.crm.controller.admin.product;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSetByFlatMap;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.collection.SetUtils;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.crm.controller.admin.product.vo.product.CrmProductPageReqVO;
import com.ymitcloud.module.crm.controller.admin.product.vo.product.CrmProductRespVO;
import com.ymitcloud.module.crm.controller.admin.product.vo.product.CrmProductSaveReqVO;
import com.ymitcloud.module.crm.convert.product.CrmProductConvert;
import com.ymitcloud.module.crm.dal.dataobject.product.CrmProductCategoryDO;
import com.ymitcloud.module.crm.dal.dataobject.product.CrmProductDO;
import com.ymitcloud.module.crm.service.product.CrmProductCategoryService;
import com.ymitcloud.module.crm.service.product.CrmProductService;
import com.ymitcloud.module.system.api.user.AdminUserApi;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - CRM 产品
 */

@RestController
@RequestMapping("/crm/product")
@Validated
public class CrmProductController {

    @Resource
    private CrmProductService productService;
    @Resource
    private CrmProductCategoryService productCategoryService;

    @Resource
    private AdminUserApi adminUserApi;


    /**
     * 创建产品
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('crm:product:create')")
    public CommonResult<Long> createProduct(@Valid @RequestBody CrmProductSaveReqVO createReqVO) {
        return success(productService.createProduct(createReqVO));
    }


    /**
     * 更新产品
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('crm:product:update')")
    public CommonResult<Boolean> updateProduct(@Valid @RequestBody CrmProductSaveReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return success(true);
    }


    /**
     * 删除产品
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('crm:product:delete')")
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return success(true);
    }


    /**
     * 获得产品
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('crm:product:query')")
    public CommonResult<CrmProductRespVO> getProduct(@RequestParam("id") Long id) {
        CrmProductDO product = productService.getProduct(id);
        if (product == null) {
            return success(null);
        }

        Map<Long, AdminUserRespDTO> userMap = adminUserApi
                .getUserMap(SetUtils.asSet(Long.valueOf(product.getCreator()), product.getOwnerUserId()));

        CrmProductCategoryDO category = productCategoryService.getProductCategory(product.getCategoryId());
        return success(CrmProductConvert.INSTANCE.convert(product, userMap, category));
    }


    /**
     * 获得产品分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('crm:product:query')")
    public CommonResult<PageResult<CrmProductRespVO>> getProductPage(@Valid CrmProductPageReqVO pageVO) {
        PageResult<CrmProductDO> pageResult = productService.getProductPage(pageVO);
        return success(new PageResult<>(getProductDetailList(pageResult.getList()), pageResult.getTotal()));
    }


    /**
     * 导出产品 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('crm:product:export')")
    @OperateLog(type = EXPORT)
    public void exportProductExcel(@Valid CrmProductPageReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CrmProductDO> list = productService.getProductPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "产品.xls", "数据", CrmProductRespVO.class, getProductDetailList(list));

    }

    private List<CrmProductRespVO> getProductDetailList(List<CrmProductDO> list) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(
                convertSetByFlatMap(list, user -> Stream.of(Long.valueOf(user.getCreator()), user.getOwnerUserId())));

        List<CrmProductCategoryDO> productCategoryList = productCategoryService
                .getProductCategoryList(convertSet(list, CrmProductDO::getCategoryId));

        return CrmProductConvert.INSTANCE.convertList(list, userMap, productCategoryList);
    }

}
