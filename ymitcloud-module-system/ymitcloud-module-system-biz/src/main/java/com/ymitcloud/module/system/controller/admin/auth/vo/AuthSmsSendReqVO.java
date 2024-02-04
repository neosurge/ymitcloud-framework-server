package com.ymitcloud.module.system.controller.admin.auth.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.system.enums.sms.SmsSceneEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 发送手机验证码 Request VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthSmsSendReqVO {
    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;
    /**
     * 短信场景
     */
    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;

}
