package com.ymitcloud.module.trade.controller.app.order.vo;



import lombok.Data;

/** 用户 App - 交易订单创建 */
@Data
public class AppTradeOrderCreateRespVO {

    /** 订单编号*/
    private Long id;

    /** 支付订单编号*/

    private Long payOrderId;

}
