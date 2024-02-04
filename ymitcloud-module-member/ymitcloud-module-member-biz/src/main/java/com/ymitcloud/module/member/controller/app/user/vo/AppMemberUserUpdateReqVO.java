package com.ymitcloud.module.member.controller.app.user.vo;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

/** 用户 App - 会员用户更新 Request VO */
@Data
public class AppMemberUserUpdateReqVO {

    /** 用户昵称*/
    private String nickname;

    /** 头像*/
    @URL(message = "头像必须是 URL 格式")
    private String avatar;

}
