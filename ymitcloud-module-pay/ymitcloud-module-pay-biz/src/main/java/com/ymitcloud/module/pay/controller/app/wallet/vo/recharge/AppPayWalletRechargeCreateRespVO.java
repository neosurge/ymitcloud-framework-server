package com.ymitcloud.module.pay.controller.app.wallet.vo.recharge;



import lombok.Data;

/** 
 * 用户 APP - 创建钱包充值 Resp VO
 */
@Data
public class AppPayWalletRechargeCreateRespVO {

    /** 钱包充值编号*/
    private Long id;

    /** 支付订单编号*/

    private Long payOrderId;

}
