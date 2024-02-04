package com.ymitcloud.module.pay.controller.app.order;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserType;

import java.util.Map;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.pay.core.enums.channel.PayChannelEnum;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderRespVO;
import com.ymitcloud.module.pay.controller.admin.order.vo.PayOrderSubmitRespVO;
import com.ymitcloud.module.pay.controller.app.order.vo.AppPayOrderSubmitReqVO;
import com.ymitcloud.module.pay.controller.app.order.vo.AppPayOrderSubmitRespVO;
import com.ymitcloud.module.pay.convert.order.PayOrderConvert;
import com.ymitcloud.module.pay.framework.pay.core.WalletPayClient;
import com.ymitcloud.module.pay.service.order.PayOrderService;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 支付订单
 */

@RestController
@RequestMapping("/pay/order")
@Validated
@Slf4j
public class AppPayOrderController {

    @Resource
    private PayOrderService payOrderService;


    /**
     * 获得支付订单
     * 
     * @param id 编号
     * @return
     */
    // TODO 云码：临时 demo，技术打样。
    @GetMapping("/get")

    public CommonResult<PayOrderRespVO> getOrder(@RequestParam("id") Long id) {
        return success(PayOrderConvert.INSTANCE.convert(payOrderService.getOrder(id)));
    }


    /**
     * 提交支付订单
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/submit")
    public CommonResult<AppPayOrderSubmitRespVO> submitPayOrder(@RequestBody AppPayOrderSubmitReqVO reqVO) {
        // 1. 钱包支付事，需要额外传 user_id 和 user_type
        if (Objects.equals(reqVO.getChannelCode(), PayChannelEnum.WALLET.getCode())) {
            Map<String, String> channelExtras = reqVO.getChannelExtras() == null ? Maps.newHashMapWithExpectedSize(2)
                    : reqVO.getChannelExtras();

            channelExtras.put(WalletPayClient.USER_ID_KEY, String.valueOf(getLoginUserId()));
            channelExtras.put(WalletPayClient.USER_TYPE_KEY, String.valueOf(getLoginUserType()));
            reqVO.setChannelExtras(channelExtras);
        }

        // 2. 提交支付
        PayOrderSubmitRespVO respVO = payOrderService.submitOrder(reqVO, getClientIP());
        return success(PayOrderConvert.INSTANCE.convert3(respVO));
    }

}
