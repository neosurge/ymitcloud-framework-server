package com.ymitcloud.module.member.controller.admin.signin.vo.record;




import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - 签到记录 */
@Data
public class MemberSignInRecordRespVO {

    /** 签到自增 id*/
    private Long id;

    /** 签到用户*/
    private Long userId;

    /** 昵称*/
    private String nickname;

    /** 第几天签到*/
    private Integer day;

    /** 签到的积分*/
    private Integer point;

    /** 签到时间*/

    private LocalDateTime createTime;

}
