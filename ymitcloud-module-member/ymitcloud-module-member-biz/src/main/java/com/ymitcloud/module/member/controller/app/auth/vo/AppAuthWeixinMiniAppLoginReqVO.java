package com.ymitcloud.module.member.controller.app.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
/** 用户 APP - 微信小程序手机登录 Request VO */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthWeixinMiniAppLoginReqVO {

    /** 手机 code,小程序通过 wx.getPhoneNumber 方法获得*/
    @NotEmpty(message = "手机 code 不能为空")
    private String phoneCode;

    /** 登录 code,小程序通过 wx.login 方法获得*/
    @NotEmpty(message = "登录 code 不能为空")
    private String loginCode;

    /** state*/
    @NotEmpty(message = "state 不能为空")
    private String state;

}
