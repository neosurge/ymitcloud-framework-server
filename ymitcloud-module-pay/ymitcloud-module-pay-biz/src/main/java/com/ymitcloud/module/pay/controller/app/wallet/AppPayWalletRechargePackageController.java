package com.ymitcloud.module.pay.controller.app.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.ArrayList;
import java.util.List;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.pay.controller.app.wallet.vo.recharge.AppPayWalletPackageRespVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 钱包充值套餐
 */

@RestController
@RequestMapping("/pay/wallet-recharge-package")
@Validated
@Slf4j
public class AppPayWalletRechargePackageController {


    /**
     * 获得钱包充值套餐列表
     * 
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppPayWalletPackageRespVO>> getWalletRechargePackageList() {
        // 只查询开启；需要按照 payPrice 排序；
        List<AppPayWalletPackageRespVO> list = new ArrayList<>();
        list.add(new AppPayWalletPackageRespVO().setId(1L).setName("土豆").setPayPrice(10).setBonusPrice(2));
        list.add(new AppPayWalletPackageRespVO().setId(2L).setName("番茄").setPayPrice(20).setBonusPrice(5));
        return success(list);
    }

}
