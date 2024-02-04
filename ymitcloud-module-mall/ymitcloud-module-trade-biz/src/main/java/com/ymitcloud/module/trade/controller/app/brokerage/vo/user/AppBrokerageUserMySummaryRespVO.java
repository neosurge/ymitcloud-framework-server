package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;



import lombok.Data;

/** 用户 App - 个人分销统计 */
@Data
public class AppBrokerageUserMySummaryRespVO {

    /** 昨天的佣金，单位：分*/
    private Integer yesterdayPrice;

    /** 提现的佣金，单位：分*/
    private Integer withdrawPrice;

    /** 可用的佣金，单位：分*/
    private Integer brokeragePrice;

    /** 冻结的佣金，单位：分*/
    private Integer frozenPrice;

    /** 分销用户数量（一级）*/
    private Long firstBrokerageUserCount;

    /** 分销用户数量（二级）*/

    private Long secondBrokerageUserCount;

}
