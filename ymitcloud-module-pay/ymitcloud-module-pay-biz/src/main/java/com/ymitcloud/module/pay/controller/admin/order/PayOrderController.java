package com.ymitcloud.module.pay.controller.admin.order;



import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.ymitcloud.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserType;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.framework.pay.core.enums.channel.PayChannelEnum;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderDetailsRespVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderExcelVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderExportReqVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderPageItemRespVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderPageReqVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderRespVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderSubmitReqVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderSubmitRespVO;
import com.ymitcloud.module.pay.convert.order.PayOrderConvert;
import com.ymitcloud.module.pay.dal.dataobject.app.PayAppDO;
import com.ymitcloud.module.pay.dal.dataobject.order.PayOrderDO;
import com.ymitcloud.module.pay.dal.dataobject.order.PayOrderExtensionDO;
import com.ymitcloud.module.pay.framework.pay.core.WalletPayClient;
import com.ymitcloud.module.pay.service.app.PayAppService;
import com.ymitcloud.module.pay.service.order.PayOrderService;

import cn.hutool.core.collection.CollectionUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 管理后台 - 支付订单
 */

@RestController
@RequestMapping("/pay/order")
@Validated
public class PayOrderController {

    @Resource
    private PayOrderService orderService;
    @Resource
    private PayAppService appService;


    /**
     * 获得支付订单
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('pay:order:query')")
    public CommonResult<PayOrderRespVO> getOrder(@RequestParam("id") Long id) {
        return success(PayOrderConvert.INSTANCE.convert(orderService.getOrder(id)));
    }


    /**
     * 获得支付订单详情
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-detail")

    @PreAuthorize("@ss.hasPermission('pay:order:query')")
    public CommonResult<PayOrderDetailsRespVO> getOrderDetail(@RequestParam("id") Long id) {
        PayOrderDO order = orderService.getOrder(id);
        if (order == null) {
            return success(null);
        }

        // 拼接返回
        PayAppDO app = appService.getApp(order.getAppId());
        PayOrderExtensionDO orderExtension = orderService.getOrderExtension(order.getExtensionId());
        return success(PayOrderConvert.INSTANCE.convert(order, orderExtension, app));
    }


    /**
     * 提交支付订单
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/submit")
    public CommonResult<PayOrderSubmitRespVO> submitPayOrder(@RequestBody PayOrderSubmitReqVO reqVO) {
        // 1. 钱包支付事，需要额外传 user_id 和 user_type
        if (Objects.equals(reqVO.getChannelCode(), PayChannelEnum.WALLET.getCode())) {
            Map<String, String> channelExtras = reqVO.getChannelExtras() == null ? Maps.newHashMapWithExpectedSize(2)
                    : reqVO.getChannelExtras();

            channelExtras.put(WalletPayClient.USER_ID_KEY, String.valueOf(getLoginUserId()));
            channelExtras.put(WalletPayClient.USER_TYPE_KEY, String.valueOf(getLoginUserType()));
            reqVO.setChannelExtras(channelExtras);
        }

        // 2. 提交支付
        PayOrderSubmitRespVO respVO = orderService.submitOrder(reqVO, getClientIP());
        return success(respVO);
    }


    /**
     * 获得支付订单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:order:query')")
    public CommonResult<PageResult<PayOrderPageItemRespVO>> getOrderPage(@Valid PayOrderPageReqVO pageVO) {
        PageResult<PayOrderDO> pageResult = orderService.getOrderPage(pageVO);
        if (CollectionUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        // 拼接返回
        Map<Long, PayAppDO> appMap = appService.getAppMap(convertList(pageResult.getList(), PayOrderDO::getAppId));
        return success(PayOrderConvert.INSTANCE.convertPage(pageResult, appMap));
    }


    /**
     * 导出支付订单 Excel
     * 
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('pay:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid PayOrderExportReqVO exportReqVO, HttpServletResponse response)
            throws IOException {
        List<PayOrderDO> list = orderService.getOrderList(exportReqVO);
        if (CollectionUtil.isEmpty(list)) {
            ExcelUtils.write(response, "支付订单.xls", "数据", PayOrderExcelVO.class, new ArrayList<>());

            return;
        }

        // 拼接返回
        Map<Long, PayAppDO> appMap = appService.getAppMap(convertList(list, PayOrderDO::getAppId));
        List<PayOrderExcelVO> excelList = PayOrderConvert.INSTANCE.convertList(list, appMap);
        // 导出 Excel
        ExcelUtils.write(response, "支付订单.xls", "数据", PayOrderExcelVO.class, excelList);
    }

}
