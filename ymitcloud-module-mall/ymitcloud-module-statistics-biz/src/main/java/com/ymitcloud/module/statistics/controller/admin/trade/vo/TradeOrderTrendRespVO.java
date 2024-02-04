package com.ymitcloud.module.statistics.controller.admin.trade.vo;



import lombok.Data;

/** 管理后台 - 订单量趋势统计 */
@Data
public class TradeOrderTrendRespVO {

    /** 日期*/
    private String date;

    /** 订单数量*/
    private Integer orderPayCount;

    /** 订单支付金额*/

    private Integer orderPayPrice;

}
