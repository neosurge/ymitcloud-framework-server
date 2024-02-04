package com.ymitcloud.module.pay.controller.admin.wallet;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageCreateReqVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackagePageReqVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageRespVO;
import com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageUpdateReqVO;
import com.ymitcloud.module.pay.convert.wallet.WalletRechargePackageConvert;
import com.ymitcloud.module.pay.dal.dataobject.wallet.PayWalletRechargePackageDO;
import com.ymitcloud.module.pay.service.wallet.PayWalletRechargePackageService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 钱包充值套餐
 */

@RestController
@RequestMapping("/pay/wallet-recharge-package")
@Validated
public class PayWalletRechargePackageController {

    @Resource
    private PayWalletRechargePackageService walletRechargePackageService;


    /**
     * 创建钱包充值套餐
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('pay:wallet-recharge-package:create')")
    public CommonResult<Long> createWalletRechargePackage(
            @Valid @RequestBody WalletRechargePackageCreateReqVO createReqVO) {
        return success(walletRechargePackageService.createWalletRechargePackage(createReqVO));
    }

    /**
     * 更新钱包充值套餐
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('pay:wallet-recharge-package:update')")
    public CommonResult<Boolean> updateWalletRechargePackage(
            @Valid @RequestBody WalletRechargePackageUpdateReqVO updateReqVO) {

        walletRechargePackageService.updateWalletRechargePackage(updateReqVO);
        return success(true);
    }


    /**
     * 删除钱包充值套餐
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('pay:wallet-recharge-package:delete')")
    public CommonResult<Boolean> deleteWalletRechargePackage(@RequestParam("id") Long id) {
        walletRechargePackageService.deleteWalletRechargePackage(id);
        return success(true);
    }


    /**
     * 获得钱包充值套餐
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('pay:wallet-recharge-package:query')")
    public CommonResult<WalletRechargePackageRespVO> getWalletRechargePackage(@RequestParam("id") Long id) {
        PayWalletRechargePackageDO walletRechargePackage = walletRechargePackageService.getWalletRechargePackage(id);
        return success(WalletRechargePackageConvert.INSTANCE.convert(walletRechargePackage));
    }


    /**
     * 获得钱包充值套餐分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('pay:wallet-recharge-package:query')")
    public CommonResult<PageResult<WalletRechargePackageRespVO>> getWalletRechargePackagePage(
            @Valid WalletRechargePackagePageReqVO pageVO) {
        PageResult<PayWalletRechargePackageDO> pageResult = walletRechargePackageService
                .getWalletRechargePackagePage(pageVO);

        return success(WalletRechargePackageConvert.INSTANCE.convertPage(pageResult));
    }

}
