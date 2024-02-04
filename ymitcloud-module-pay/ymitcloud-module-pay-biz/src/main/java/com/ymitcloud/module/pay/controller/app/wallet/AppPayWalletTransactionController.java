package com.ymitcloud.module.pay.controller.app.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionPageReqVO;
import com.ymitcloud.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import com.ymitcloud.module.pay.convert.wallet.PayWalletTransactionConvert;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import com.ymitcloud.module.pay.service.wallet.PayWalletTransactionService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 钱包余额明细
 */

@RestController
@RequestMapping("/pay/wallet-transaction")
@Validated
@Slf4j
public class AppPayWalletTransactionController {

    @Resource
    private PayWalletTransactionService payWalletTransactionService;


    /**
     * 获得钱包流水分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppPayWalletTransactionRespVO>> getWalletTransactionPage(
            @Valid AppPayWalletTransactionPageReqVO pageReqVO) {
        if (true) {
            PageResult<AppPayWalletTransactionRespVO> result = new PageResult<>(10L);

            result.getList().add(
                    new AppPayWalletTransactionRespVO().setPrice(1L).setTitle("测试").setCreateTime(LocalDateTime.now()));
            result.getList().add(new AppPayWalletTransactionRespVO().setPrice(-1L).setTitle("测试2")
                    .setCreateTime(LocalDateTime.now()));
            return success(result);
        }
        PageResult<PayWalletTransactionDO> result = payWalletTransactionService
                .getWalletTransactionPage(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), pageReqVO);

        return success(PayWalletTransactionConvert.INSTANCE.convertPage(result));
    }
}
