package com.ymitcloud.module.member.controller.app.signin.vo.record;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 App - 签到记录 */
@Data
public class AppMemberSignInRecordRespVO {

    /** 第几天签到*/
    private Integer day;

    /** 签到的分数*/
    private Integer point;

    /** 签到的经验*/
    private Integer experience;

    /** 签到时间*/

    private LocalDateTime createTime;

}
