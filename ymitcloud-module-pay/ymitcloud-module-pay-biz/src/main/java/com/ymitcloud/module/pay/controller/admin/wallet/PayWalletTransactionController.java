package com.ymitcloud.module.pay.controller.admin.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionPageReqVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import com.ymitcloud.module.pay.convert.wallet.PayWalletTransactionConvert;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import com.ymitcloud.module.pay.service.wallet.PayWalletTransactionService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 钱包余额明细
 */

@RestController
@RequestMapping("/pay/wallet-transaction")
@Validated
@Slf4j
public class PayWalletTransactionController {

    @Resource
    private PayWalletTransactionService payWalletTransactionService;


    /**
     * 获得钱包流水分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('pay:wallet:query')")
    public CommonResult<PageResult<PayWalletTransactionRespVO>> getWalletTransactionPage(
            @Valid PayWalletTransactionPageReqVO pageReqVO) {
        PageResult<PayWalletTransactionDO> result = payWalletTransactionService.getWalletTransactionPage(pageReqVO);
        return success(PayWalletTransactionConvert.INSTANCE.convertPage2(result));
    }

}
