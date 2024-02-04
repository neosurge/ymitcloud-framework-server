package com.ymitcloud.module.member.controller.app.auth.vo;

import cn.hutool.core.util.StrUtil;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.system.enums.social.SocialTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;

/**
 * 用户 APP - 手机 + 密码登录 Request VO,如果登录并绑定社交用户，需要传递 social 开头的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthLoginReqVO {

    /** 手机号 */
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;

    /** 密码 */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    // ========== 绑定社交登录时，需要传递如下参数 ==========

    /** 社交平台的类型，参见 SocialTypeEnum 枚举值 */
    @InEnum(SocialTypeEnum.class)
    private Integer socialType;

    /** 授权码 */
    private String socialCode;

    /** state */
    private String socialState;

    @AssertTrue(message = "授权码不能为空")
    public boolean isSocialCodeValid() {
        return socialType == null || StrUtil.isNotEmpty(socialCode);
    }

    @AssertTrue(message = "授权 state 不能为空")
    public boolean isSocialState() {
        return socialType == null || StrUtil.isNotEmpty(socialState);
    }

}