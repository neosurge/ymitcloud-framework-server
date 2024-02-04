package com.ymitcloud.module.pay.controller.app.wallet.vo.recharge;



import lombok.Data;

/** 用户 APP - 用户充值套餐 */
@Data
public class AppPayWalletPackageRespVO {

    /** 编号*/
    private Long id;
    /** 套餐名*/
    private String name;

    /** 支付金额*/
    private Integer payPrice;
    /** 赠送金额*/

    private Integer bonusPrice;

}
