package com.ymitcloud.module.member.controller.app.user.vo;

import com.ymitcloud.framework.common.validation.Mobile;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


/** 用户 APP - 重置密码 Request VO */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppMemberUserResetPasswordReqVO {


    /** 新密码*/

    @NotEmpty(message = "新密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;


    /** 手机验证码*/

    @NotEmpty(message = "手机验证码不能为空")
    @Length(min = 4, max = 6, message = "手机验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机验证码必须都是数字")
    private String code;


    /** 手机号*/

    @NotBlank(message = "手机号不能为空")
    @Mobile
    private String mobile;

}
