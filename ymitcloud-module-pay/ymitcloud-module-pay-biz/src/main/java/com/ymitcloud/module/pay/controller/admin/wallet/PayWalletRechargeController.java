package com.ymitcloud.module.pay.controller.admin.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.pay.api.notify.dto.PayOrderNotifyReqDTO;
import com.ymitcloud.module.pay.api.notify.dto.PayRefundNotifyReqDTO;
import com.ymitcloud.module.pay.service.wallet.PayWalletRechargeService;



import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
/**
 * 管理后台 - 钱包充值
 */

@RestController
@RequestMapping("/pay/wallet-recharge")
@Validated
@Slf4j
public class PayWalletRechargeController {

    @Resource
    private PayWalletRechargeService walletRechargeService;


    /**
     * 更新钱包充值为已充值
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-paid")

    @PermitAll // 无需登录， 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateWalletRechargerPaid(@Valid @RequestBody PayOrderNotifyReqDTO notifyReqDTO) {
        walletRechargeService.updateWalletRechargerPaid(Long.valueOf(notifyReqDTO.getMerchantOrderId()),
                notifyReqDTO.getPayOrderId());
        return success(true);
    }


    /**
     * 发起钱包充值退款
     * 
     * @param id 编号
     * @return
     */
    // TODO @jason：发起退款，要 post 操作哈；
    @GetMapping("/refund")

    public CommonResult<Boolean> refundWalletRecharge(@RequestParam("id") Long id) {
        walletRechargeService.refundWalletRecharge(id, getClientIP());
        return success(true);
    }


    /**
     * 更新钱包充值为已退款
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-refunded")
    @PermitAll // 无需登录， 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateWalletRechargeRefunded(@RequestBody PayRefundNotifyReqDTO notifyReqDTO) {
        walletRechargeService.updateWalletRechargeRefunded(Long.valueOf(notifyReqDTO.getMerchantOrderId()),
                notifyReqDTO.getPayRefundId());

        return success(true);
    }

}
