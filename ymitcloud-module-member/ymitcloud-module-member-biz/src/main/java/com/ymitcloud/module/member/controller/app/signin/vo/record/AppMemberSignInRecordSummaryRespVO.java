package com.ymitcloud.module.member.controller.app.signin.vo.record;


import lombok.Data;

/** 用户 App - 个人签到统计 */
@Data
public class AppMemberSignInRecordSummaryRespVO {

    /** 总签到天数*/
    private Integer totalDay;

    /** 连续签到第 x 天*/
    private Integer continuousDay;

    /** 今天是否已签到*/
    private Boolean todaySignIn;

}
