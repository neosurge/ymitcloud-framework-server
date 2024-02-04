package com.ymitcloud.module.member.controller.app.auth.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.system.enums.sms.SmsSceneEnum;



import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


/** 用户 APP - 校验手机验证码 Request VO */

@Data
@Accessors(chain = true)
public class AppAuthSmsValidateReqVO {


    /** 手机号", example = "15601691234")
    @Mobile
    private String mobile;

    /** 发送场景,对应 SmsSceneEnum 枚举", example = "1")

    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;


    /** 手机验证码*/

    @NotEmpty(message = "手机验证码不能为空")
    @Length(min = 4, max = 6, message = "手机验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机验证码必须都是数字")
    private String code;

}
