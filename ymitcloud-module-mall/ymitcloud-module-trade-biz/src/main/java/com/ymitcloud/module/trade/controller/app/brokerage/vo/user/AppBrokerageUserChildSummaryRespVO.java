package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 App - 下级分销统计 */
@Data
public class AppBrokerageUserChildSummaryRespVO {

    /** 用户编号*/
    private Long id;

    /** 用户昵称*/
    private String nickname;

    /** 用户头像*/
    private String avatar;

    /** 佣金金额，单位：分*/
    private Integer brokeragePrice;

    /** 分销订单数量*/
    private Integer brokerageOrderCount;

    /** 分销用户数量*/
    private Integer brokerageUserCount;

    /** 绑定推广员的时间*/

    private LocalDateTime brokerageTime;

}
