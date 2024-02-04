package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;



import lombok.Data;

/** 用户 App - 分销排行用户（基于用户量） */
@Data
public class AppBrokerageUserRankByPriceRespVO {

    /** 用户编号*/
    private Long id;

    /** 用户昵称*/
    private String nickname;

    /** 用户头像*/
    private String avatar;

    /** 佣金金额*/

    private Integer brokeragePrice;

}
