package com.ymitcloud.module.statistics.controller.admin.trade.vo;



import lombok.Data;

/** 管理后台 - 交易订单数量 */
@Data
public class TradeOrderCountRespVO {

    /** 待发货*/
    private Long undelivered;

    /** 待核销*/
    private Long pickUp;

    /** 退款中*/
    private Long afterSaleApply;

    /** 提现待审核*/

    private Long auditingWithdraw;

}
