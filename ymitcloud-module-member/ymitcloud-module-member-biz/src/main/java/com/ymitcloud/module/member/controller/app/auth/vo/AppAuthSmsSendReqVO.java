package com.ymitcloud.module.member.controller.app.auth.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.system.enums.sms.SmsSceneEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/** 用户 APP - 发送手机验证码 Request VO */
@Data
@Accessors(chain = true)
public class AppAuthSmsSendReqVO {

    /** 手机号*/
    @Mobile
    private String mobile;

    /** 发送场景,对应 SmsSceneEnum 枚举 */
    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;

}
