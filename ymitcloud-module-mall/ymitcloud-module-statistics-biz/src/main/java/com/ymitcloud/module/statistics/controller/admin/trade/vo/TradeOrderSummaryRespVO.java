package com.ymitcloud.module.statistics.controller.admin.trade.vo;



import lombok.Data;

/** 管理后台 - 交易订单统计 */
@Data
public class TradeOrderSummaryRespVO {

    /** 支付订单商品数*/
    private Integer orderPayCount;

    /** 总支付金额*/

    private Integer orderPayPrice;

}
