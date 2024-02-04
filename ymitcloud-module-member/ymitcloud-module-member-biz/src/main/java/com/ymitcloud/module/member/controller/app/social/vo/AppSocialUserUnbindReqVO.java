package com.ymitcloud.module.member.controller.app.social.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.system.enums.social.SocialTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/** 用户 APP - 取消社交绑定 Request VO */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppSocialUserUnbindReqVO {

    /** 社交平台的类型，参见 SocialTypeEnum 枚举值*/
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer type;

    /** 社交用户的 openid*/
    @NotEmpty(message = "社交用户的 openid 不能为空")
    private String openid;

}