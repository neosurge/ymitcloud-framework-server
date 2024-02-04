package com.ymitcloud.module.statistics.controller.admin.member.vo;


import lombok.Data;

/** 管理后台 - 会员分析数据 */
@Data
public class MemberAnalyseDataRespVO {

    /** 会员数量*/
    private Integer registerUserCount;

    /** 活跃用户数量*/
    private Integer visitUserCount;

    /** 充值会员数量*/

    private Integer rechargeUserCount;

}
