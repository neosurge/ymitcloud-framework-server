package com.ymitcloud.module.member.controller.app.auth.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.system.enums.sms.SmsSceneEnum;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


// TODO 云码：code review 相关逻辑
/** 用户 APP - 校验验证码 Request VO */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthCheckCodeReqVO {


    /**
     * 手机号", example = "15601691234")
     * 
     * @NotBlank(message = "手机号不能为空")
     * @Mobile private String mobile;
     * 
     *         /** 手机验证码
     */

    @NotBlank(message = "手机验证码不能为空")
    @Length(min = 4, max = 6, message = "手机验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "手机验证码必须都是数字")
    private String code;


    /**
     * 发送场景,对应 SmsSceneEnum 枚举
     */

    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;

}
