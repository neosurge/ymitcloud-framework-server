package com.ymitcloud.module.member.controller.app.signin.vo.config;



import lombok.Data;

/** 用户 App - 签到规则 */
@Data
public class AppMemberSignInConfigRespVO {

    /** 签到第 x 天*/
    private Integer day;

    /** 奖励积分*/

    private Integer point;

}
