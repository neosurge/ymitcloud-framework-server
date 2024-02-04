package com.ymitcloud.module.pay.controller.app.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserType;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateReqVO;
import com.ymitcloud.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateRespVO;
import com.ymitcloud.module.pay.convert.wallet.PayWalletRechargeConvert;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import com.ymitcloud.module.pay.service.wallet.PayWalletRechargeService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 钱包充值
 */

@RestController
@RequestMapping("/pay/wallet-recharge")
@Validated
@Slf4j
public class AppPayWalletRechargeController {

    @Resource
    private PayWalletRechargeService walletRechargeService;


    /**
     * 创建钱包充值记录（发起充值）
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/create")
    public CommonResult<AppPayWalletRechargeCreateRespVO> createWalletRecharge(
            @Valid @RequestBody AppPayWalletRechargeCreateReqVO reqVO) {
        PayWalletRechargeDO walletRecharge = walletRechargeService.createWalletRecharge(getLoginUserId(),
                getLoginUserType(), getClientIP(), reqVO);

        return success(PayWalletRechargeConvert.INSTANCE.convert(walletRecharge));
    }

}
