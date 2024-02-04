package com.ymitcloud.module.pay.controller.app.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.pay.controller.app.wallet.vo.wallet.AppPayWalletRespVO;
import com.ymitcloud.module.pay.convert.wallet.PayWalletConvert;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletDO;
import com.ymitcloud.module.pay.service.wallet.PayWalletService;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 钱包
 * 
 * @author jason
 */

@RestController
@RequestMapping("/pay/wallet")
@Validated
@Slf4j
public class AppPayWalletController {

    @Resource
    private PayWalletService payWalletService;


    /**
     * 获取钱包
     * 
     * @return
     */
    @GetMapping("/get")

    @PreAuthenticated
    public CommonResult<AppPayWalletRespVO> getPayWallet() {
        PayWalletDO wallet = payWalletService.getOrCreateWallet(getLoginUserId(), UserTypeEnum.MEMBER.getValue());
        return success(PayWalletConvert.INSTANCE.convert(wallet));
    }

}
