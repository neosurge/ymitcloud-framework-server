package com.ymitcloud.module.statistics.controller.admin.member.vo;



import lombok.Data;

/** 管理后台 - 会员统计 */
@Data
public class MemberSummaryRespVO {

    /** 会员数量*/
    private Integer userCount;

    /** 充值会员数量*/
    private Integer rechargeUserCount;

    /** 充值金额，单位：分*/
    private Integer rechargePrice;

    // TODO @疯狂：要不干脆这个字段改成：orderPayPrice？？
    /** 支出金额，单位：分*/

    private Integer expensePrice; // 只计算 mall 交易订单的支付金额，不考虑退款

}
