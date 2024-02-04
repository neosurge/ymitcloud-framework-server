package com.ymitcloud.module.pay.api.order;

import com.ymitcloud.module.pay.api.order.dto.PayOrderCreateReqDTO;
import com.ymitcloud.module.pay.api.order.dto.PayOrderRespDTO;
import com.ymitcloud.module.pay.convert.order.PayOrderConvert;
import com.ymitcloud.module.pay.dal.dataobject.order.PayOrderDO;
import com.ymitcloud.module.pay.service.order.PayOrderService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 支付单 API 实现类
 *

 * @author 

 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

    @Override
    public void updatePayOrderPrice(Long id, Integer payPrice) {
        payOrderService.updatePayOrderPrice(id, payPrice);
    }

}
