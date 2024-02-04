package com.ymitcloud.module.product.controller.admin.spu;



import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.module.product.enums.ErrorCodeConstants.SPU_NOT_EXISTS;


import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuCreateReqVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuDetailRespVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuExcelVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuExportReqVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuPageReqVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuRespVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuSimpleRespVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuUpdateReqVO;
import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuUpdateStatusReqVO;
import com.ymitcloud.module.product.convert.spu.ProductSpuConvert;
import com.ymitcloud.module.product.dal.dataobject.sku.ProductSkuDO;
import com.ymitcloud.module.product.dal.dataobject.spu.ProductSpuDO;
import com.ymitcloud.module.product.enums.spu.ProductSpuStatusEnum;
import com.ymitcloud.module.product.service.sku.ProductSkuService;
import com.ymitcloud.module.product.service.spu.ProductSpuService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商品 SPU
 */

@RestController
@RequestMapping("/product/spu")
@Validated
public class ProductSpuController {

    @Resource
    private ProductSpuService productSpuService;
    @Resource
    private ProductSkuService productSkuService;


    /**
     * 创建商品 SPU
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('product:spu:create')")
    public CommonResult<Long> createProductSpu(@Valid @RequestBody ProductSpuCreateReqVO createReqVO) {
        return success(productSpuService.createSpu(createReqVO));
    }


    /**
     * 更新商品 SPU
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('product:spu:update')")
    public CommonResult<Boolean> updateSpu(@Valid @RequestBody ProductSpuUpdateReqVO updateReqVO) {
        productSpuService.updateSpu(updateReqVO);
        return success(true);
    }


    /**
     * 更新商品 SPU Status
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-status")

    @PreAuthorize("@ss.hasPermission('product:spu:update')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody ProductSpuUpdateStatusReqVO updateReqVO) {
        productSpuService.updateSpuStatus(updateReqVO);
        return success(true);
    }


    /**
     * 删除商品 SPU
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('product:spu:delete')")
    public CommonResult<Boolean> deleteSpu(@RequestParam("id") Long id) {
        productSpuService.deleteSpu(id);
        return success(true);
    }


    /**
     * 获得商品 SPU 明细
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-detail")

    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<ProductSpuDetailRespVO> getSpuDetail(@RequestParam("id") Long id) {
        // 获得商品 SPU
        ProductSpuDO spu = productSpuService.getSpu(id);
        if (spu == null) {
            throw exception(SPU_NOT_EXISTS);
        }
        // 查询商品 SKU
        List<ProductSkuDO> skus = productSkuService.getSkuListBySpuId(spu.getId());
        return success(ProductSpuConvert.INSTANCE.convertForSpuDetailRespVO(spu, skus));
    }


    /**
     * 获得商品 SPU 精简列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<List<ProductSpuSimpleRespVO>> getSpuSimpleList() {
        List<ProductSpuDO> list = productSpuService.getSpuListByStatus(ProductSpuStatusEnum.ENABLE.getStatus());
        // 降序排序后，返回给前端
        list.sort(Comparator.comparing(ProductSpuDO::getSort).reversed());
        return success(ProductSpuConvert.INSTANCE.convertList02(list));
    }


    /**
     * 获得商品 SPU 详情列表
     * 
     * @param spuIds spu 编号列表
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<List<ProductSpuDetailRespVO>> getSpuList(@RequestParam("spuIds") Collection<Long> spuIds) {
        return success(ProductSpuConvert.INSTANCE.convertForSpuDetailRespListVO(productSpuService.getSpuList(spuIds),
                productSkuService.getSkuListBySpuId(spuIds)));
    }

    /**
     * 获得商品 SPU 分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<PageResult<ProductSpuRespVO>> getSpuPage(@Valid ProductSpuPageReqVO pageVO) {
        return success(ProductSpuConvert.INSTANCE.convertPage(productSpuService.getSpuPage(pageVO)));
    }


    /**
     * 获得商品 SPU 分页 tab count
     * 
     * @return
     */
    @GetMapping("/get-count")

    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<Map<Integer, Long>> getSpuCount() {
        return success(productSpuService.getTabsCount());
    }


    /**
     * 导出商品
     * 
     * @param reqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('product:spu:export')")
    @OperateLog(type = EXPORT)
    public void exportUserList(@Validated ProductSpuExportReqVO reqVO, HttpServletResponse response)
            throws IOException {

        List<ProductSpuDO> spuList = productSpuService.getSpuList(reqVO);
        // 导出 Excel
        List<ProductSpuExcelVO> datas = ProductSpuConvert.INSTANCE.convertList03(spuList);
        ExcelUtils.write(response, "商品列表.xls", "数据", ProductSpuExcelVO.class, datas);
    }

}
