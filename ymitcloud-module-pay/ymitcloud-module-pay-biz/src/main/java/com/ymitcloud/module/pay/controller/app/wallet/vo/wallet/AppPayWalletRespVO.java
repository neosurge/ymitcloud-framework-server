package com.ymitcloud.module.pay.controller.app.wallet.vo.wallet;



import lombok.Data;

/** 用户 APP - 用户钱包 */
@Data
public class AppPayWalletRespVO {

    /** 钱包余额,单位分*/
    private Integer balance;

    /** 累计支出, 单位分*/
    private Integer totalExpense;

    /** 累计充值, 单位分*/

    private Integer totalRecharge;

}
