package com.ymitcloud.module.statistics.controller.admin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;



import lombok.Data;

import java.time.LocalDate;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;


/** 管理后台 - 交易状况统计 */
@Data
public class TradeTrendSummaryRespVO {

    /** 日期*/
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate date;

    /** 营业额*/
    private Integer turnoverPrice; // 营业额 = 商品支付金额 + 充值金额

    /** 订单支付金额*/
    private Integer orderPayPrice;

    /** 余额支付金额*/
    private Integer walletPayPrice;

    /** 订单退款金额*/
    private Integer afterSaleRefundPrice;

    /** 支付佣金金额*/
    private Integer brokerageSettlementPrice;

    /** 充值金额*/
    private Integer rechargePrice;

    /** 支出金额*/

    private Integer expensePrice; // 余额支付金额 + 支付佣金金额 + 商品退款金额

}
