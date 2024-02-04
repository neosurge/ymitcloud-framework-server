package com.ymitcloud.module.pay.api.transfer;

import com.ymitcloud.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import com.ymitcloud.module.pay.service.transfer.PayTransferService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 转账单 API 实现类
 *
 * @author jason
 */
@Service
@Validated
public class PayTransferApiImpl implements PayTransferApi {

    @Resource
    private PayTransferService payTransferService;

    @Override
    public Long createTransfer(PayTransferCreateReqDTO reqDTO) {
        return payTransferService.createTransfer(reqDTO);
    }

}
