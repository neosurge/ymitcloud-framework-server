package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;



import lombok.Data;

/** 用户 App - 分销用户信息 */
@Data
public class AppBrokerageUserRespVO {

    /** 是否有分销资格*/
    private Boolean brokerageEnabled;

    /** 可用的佣金，单位：分*/
    private Integer brokeragePrice;

    /** 冻结的佣金，单位：分*/

    private Integer frozenPrice;

}
