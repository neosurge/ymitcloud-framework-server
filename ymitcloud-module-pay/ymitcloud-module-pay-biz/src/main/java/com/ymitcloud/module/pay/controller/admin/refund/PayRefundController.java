package com.ymitcloud.module.pay.controller.admin.refund;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.pay.controller.admin.refund.vo.PayRefundDetailsRespVO;
import com.ymitcloud.module.pay.controller.admin.refund.vo.PayRefundExcelVO;
import com.ymitcloud.module.pay.controller.admin.refund.vo.PayRefundExportReqVO;
import com.ymitcloud.module.pay.controller.admin.refund.vo.PayRefundPageItemRespVO;
import com.ymitcloud.module.pay.controller.admin.refund.vo.PayRefundPageReqVO;
import com.ymitcloud.module.pay.convert.refund.PayRefundConvert;
import com.ymitcloud.module.pay.dal.dataobject.app.PayAppDO;
import com.ymitcloud.module.pay.dal.dataobject.refund.PayRefundDO;
import com.ymitcloud.module.pay.service.app.PayAppService;
import com.ymitcloud.module.pay.service.refund.PayRefundService;

import cn.hutool.core.collection.CollectionUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 退款订单
 */

@RestController
@RequestMapping("/pay/refund")
@Validated
public class PayRefundController {

    @Resource
    private PayRefundService refundService;
    @Resource
    private PayAppService appService;


    /**
     * 获得退款订单
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('pay:refund:query')")
    public CommonResult<PayRefundDetailsRespVO> getRefund(@RequestParam("id") Long id) {
        PayRefundDO refund = refundService.getRefund(id);
        if (refund == null) {
            return success(new PayRefundDetailsRespVO());
        }

        // 拼接数据
        PayAppDO app = appService.getApp(refund.getAppId());
        return success(PayRefundConvert.INSTANCE.convert(refund, app));
    }


    /**
     * 获得退款订单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:refund:query')")
    public CommonResult<PageResult<PayRefundPageItemRespVO>> getRefundPage(@Valid PayRefundPageReqVO pageVO) {
        PageResult<PayRefundDO> pageResult = refundService.getRefundPage(pageVO);
        if (CollectionUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        // 处理应用ID数据
        Map<Long, PayAppDO> appMap = appService.getAppMap(convertList(pageResult.getList(), PayRefundDO::getAppId));
        return success(PayRefundConvert.INSTANCE.convertPage(pageResult, appMap));
    }


    /**
     * 导出退款订单 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('pay:refund:export')")
    @OperateLog(type = EXPORT)
    public void exportRefundExcel(@Valid PayRefundExportReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        List<PayRefundDO> list = refundService.getRefundList(exportReqVO);
        if (CollectionUtil.isEmpty(list)) {
            ExcelUtils.write(response, "退款订单.xls", "数据", PayRefundExcelVO.class, new ArrayList<>());

            return;
        }

        // 拼接返回
        Map<Long, PayAppDO> appMap = appService.getAppMap(convertList(list, PayRefundDO::getAppId));
        List<PayRefundExcelVO> excelList = PayRefundConvert.INSTANCE.convertList(list, appMap);
        // 导出 Excel
        ExcelUtils.write(response, "退款订单.xls", "数据", PayRefundExcelVO.class, excelList);
    }

}
