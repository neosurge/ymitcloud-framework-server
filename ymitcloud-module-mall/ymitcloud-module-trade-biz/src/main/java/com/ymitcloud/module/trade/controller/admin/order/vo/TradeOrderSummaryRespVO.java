package com.ymitcloud.module.trade.controller.admin.order.vo;



import lombok.Data;

/** 管理后台 - 交易订单统计 */
@Data
public class TradeOrderSummaryRespVO {

    /** 订单数量*/
    private Long orderCount;

    /** 订单金额*/
    private Long orderPayPrice;

    /** 退款单数*/
    private Long afterSaleCount;

    /** 退款金额*/

    private Long afterSalePrice;

}
