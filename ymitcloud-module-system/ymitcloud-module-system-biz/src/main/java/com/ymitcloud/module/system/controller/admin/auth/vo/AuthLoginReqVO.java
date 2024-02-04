package com.ymitcloud.module.system.controller.admin.auth.vo;

import org.hibernate.validator.constraints.Length;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.system.enums.social.SocialTypeEnum;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 账号密码登录 Request VO，如果登录并绑定社交用户，需要传递 social 开头的参数
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginReqVO {
    /**
     * 账号
     */
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    // ========== 图片验证码相关 ==========
    /**
     * 验证码，验证码开启时，需要传递
     */
    @NotEmpty(message = "验证码不能为空", groups = CodeEnableGroup.class)
    private String captchaVerification;

    // ========== 绑定社交登录时，需要传递如下参数 ==========
    /**
     * 社交平台的类型，参见 SocialTypeEnum 枚举值
     */
    @InEnum(SocialTypeEnum.class)
    private Integer socialType;

    /**
     * 授权码
     */
    private String socialCode;
    /**
     * state
     */
    private String socialState;

    /**
     * 开启验证码的 Group
     */
    public interface CodeEnableGroup {
    }

    @AssertTrue(message = "授权码不能为空")
    public boolean isSocialCodeValid() {
        return socialType == null || StrUtil.isNotEmpty(socialCode);
    }

    @AssertTrue(message = "授权 state 不能为空")
    public boolean isSocialState() {
        return socialType == null || StrUtil.isNotEmpty(socialState);
    }

}