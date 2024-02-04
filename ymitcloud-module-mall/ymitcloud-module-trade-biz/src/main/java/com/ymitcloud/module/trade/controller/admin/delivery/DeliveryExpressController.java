package com.ymitcloud.module.trade.controller.admin.delivery;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
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
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;

import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressCreateReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressExcelVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressExportReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressPageReqVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressSimpleRespVO;
import com.ymitcloud.module.trade.controller.admin.delivery.vo.express.DeliveryExpressUpdateReqVO;
import com.ymitcloud.module.trade.convert.delivery.DeliveryExpressConvert;
import com.ymitcloud.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.ymitcloud.module.trade.service.delivery.DeliveryExpressService;


import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


/**
 * 管理后台 - 快递公司
 */

@RestController
@RequestMapping("/trade/delivery/express")
@Validated
public class DeliveryExpressController {

    @Resource
    private DeliveryExpressService deliveryExpressService;


    /**
     * 创建快递公司
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('trade:delivery:express:create')")
    public CommonResult<Long> createDeliveryExpress(@Valid @RequestBody DeliveryExpressCreateReqVO createReqVO) {
        return success(deliveryExpressService.createDeliveryExpress(createReqVO));
    }


    /**
     * 更新快递公司
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('trade:delivery:express:update')")
    public CommonResult<Boolean> updateDeliveryExpress(@Valid @RequestBody DeliveryExpressUpdateReqVO updateReqVO) {
        deliveryExpressService.updateDeliveryExpress(updateReqVO);
        return success(true);
    }


    /**
     * 删除快递公司
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('trade:delivery:express:delete')")
    public CommonResult<Boolean> deleteDeliveryExpress(@RequestParam("id") Long id) {
        deliveryExpressService.deleteDeliveryExpress(id);
        return success(true);
    }


    /**
     * 获得快递公司
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('trade:delivery:express:query')")
    public CommonResult<DeliveryExpressRespVO> getDeliveryExpress(@RequestParam("id") Long id) {
        DeliveryExpressDO deliveryExpress = deliveryExpressService.getDeliveryExpress(id);
        return success(DeliveryExpressConvert.INSTANCE.convert(deliveryExpress));
    }


    /**
     * 获取快递公司精简信息列表
     * 
     * @return
     */
    @GetMapping("/list-all-simple")
    public CommonResult<List<DeliveryExpressSimpleRespVO>> getSimpleDeliveryExpressList() {
        List<DeliveryExpressDO> list = deliveryExpressService
                .getDeliveryExpressListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(DeliveryExpressConvert.INSTANCE.convertList1(list));
    }

    /**
     * 获得快递公司分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:query')")
    public CommonResult<PageResult<DeliveryExpressRespVO>> getDeliveryExpressPage(
            @Valid DeliveryExpressPageReqVO pageVO) {

        PageResult<DeliveryExpressDO> pageResult = deliveryExpressService.getDeliveryExpressPage(pageVO);
        return success(DeliveryExpressConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 导出快递公司 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('trade:delivery:express:export')")
    @OperateLog(type = EXPORT)
    public void exportDeliveryExpressExcel(@Valid DeliveryExpressExportReqVO exportReqVO, HttpServletResponse response)
            throws IOException {

        List<DeliveryExpressDO> list = deliveryExpressService.getDeliveryExpressList(exportReqVO);
        // 导出 Excel
        List<DeliveryExpressExcelVO> dataList = DeliveryExpressConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "快递公司.xls", "数据", DeliveryExpressExcelVO.class, dataList);
    }
}
