package com.ymitcloud.module.trade.controller.app.brokerage.vo.withdraw;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 App - 分销提现 */
@Data
public class AppBrokerageWithdrawRespVO {

    /** 提现编号*/
    private Long id;

    /** 提现状态*/
    private Integer status;

    /** 提现状态名*/
    private String statusName;

    /** 提现金额，单位：分*/
    private Integer price;

    /** 提现时间*/

    private LocalDateTime createTime;

}
