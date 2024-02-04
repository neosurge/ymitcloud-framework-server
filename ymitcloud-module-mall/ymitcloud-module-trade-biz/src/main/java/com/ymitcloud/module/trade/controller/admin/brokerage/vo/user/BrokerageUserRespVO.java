package com.ymitcloud.module.trade.controller.admin.brokerage.vo.user;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 分销用户 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BrokerageUserRespVO extends BrokerageUserBaseVO {


    /** 用户编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

    // ========== 用户信息 ==========


    /** 用户头像*/
    private String avatar;
    /** 用户昵称*/

    private String nickname;

    // ========== 推广信息 ========== 注意：是包括 1 + 2 级的数据


    /** 推广用户数量*/
    private Integer brokerageUserCount;
    /** 推广订单数量*/
    private Integer brokerageOrderCount;
    /** 推广订单金额*/

    private Integer brokerageOrderPrice;

    // ========== 提现信息 ==========


    /** 已提现金额*/
    private Integer withdrawPrice;
    /** 已提现次数*/

    private Integer withdrawCount;

}
