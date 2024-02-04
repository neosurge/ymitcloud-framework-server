package com.ymitcloud.module.pay.api.refund;

import com.ymitcloud.module.pay.api.refund.dto.PayRefundCreateReqDTO;
import com.ymitcloud.module.pay.api.refund.dto.PayRefundRespDTO;
import com.ymitcloud.module.pay.convert.refund.PayRefundConvert;
import com.ymitcloud.module.pay.service.refund.PayRefundService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 退款单 API 实现类
 *

 * @author 

 */
@Service
@Validated
public class PayRefundApiImpl implements PayRefundApi {

    @Resource
    private PayRefundService payRefundService;

    @Override
    public Long createRefund(PayRefundCreateReqDTO reqDTO) {
        return payRefundService.createPayRefund(reqDTO);
    }

    @Override
    public PayRefundRespDTO getRefund(Long id) {
        return PayRefundConvert.INSTANCE.convert02(payRefundService.getRefund(id));
    }

}
