package com.ymitcloud.module.statistics.controller.admin.trade.vo;



import lombok.Data;

/** 管理后台 - 交易统计 */
@Data
public class TradeSummaryRespVO {

    /** 昨日订单数量*/
    private Integer yesterdayOrderCount;
    /** 昨日支付金额*/
    private Integer yesterdayPayPrice;

    /** 本月订单数量*/
    private Integer monthOrderCount;
    /** 本月支付金额*/

    private Integer monthPayPrice;

}
