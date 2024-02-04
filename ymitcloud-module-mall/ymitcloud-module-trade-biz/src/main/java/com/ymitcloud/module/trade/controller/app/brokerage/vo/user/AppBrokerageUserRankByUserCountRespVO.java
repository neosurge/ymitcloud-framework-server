package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;



import lombok.Data;

/** 用户 App - 分销排行用户（基于用户量） */
@Data
public class AppBrokerageUserRankByUserCountRespVO {

    /** 用户编号*/
    private Long id;

    /** 用户昵称*/
    private String nickname;

    /** 用户头像*/
    private String avatar;

    /** 邀请用户数量*/

    private Integer brokerageUserCount;

}
