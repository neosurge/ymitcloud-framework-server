package com.ymit.module.system.controller.admin.auth.vo;

import com.ymit.framework.common.validation.InEnum;
import com.ymit.module.system.enums.social.SocialTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 社交绑定登录 Request VO，使用 code 授权码 + 账号密码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthSocialLoginReqVO {
    /**
     * 社交平台的类型，参见 UserSocialTypeEnum 枚举值
     */
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer type;
    /**
     * 授权码
     */
    @NotEmpty(message = "授权码不能为空")
    private String code;
    /**
     * state
     */
    @NotEmpty(message = "state 不能为空")
    private String state;

}
