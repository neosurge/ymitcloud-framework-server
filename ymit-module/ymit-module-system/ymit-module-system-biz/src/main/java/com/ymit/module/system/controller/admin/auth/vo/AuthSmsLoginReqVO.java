package com.ymit.module.system.controller.admin.auth.vo;

import com.ymit.framework.common.validation.Mobile;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 管理后台 - 短信验证码的登录 Request VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthSmsLoginReqVO {
    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;
    /**
     * 短信验证码
     */
    @NotEmpty(message = "验证码不能为空")
    private String code;

}
