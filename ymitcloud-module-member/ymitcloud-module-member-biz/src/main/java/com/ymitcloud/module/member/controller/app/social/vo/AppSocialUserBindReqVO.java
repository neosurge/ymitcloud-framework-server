package com.ymitcloud.module.member.controller.app.social.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.system.enums.social.SocialTypeEnum;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 * 用户 APP - 社交绑定 Request VO，使用 code 授权码
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppSocialUserBindReqVO {


    /** 社交平台的类型，参见 SocialTypeEnum 枚举值 */

    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer type;


    /** 授权码 */
    @NotEmpty(message = "授权码不能为空")
    private String code;

    /** state */

    @NotEmpty(message = "state 不能为空")
    private String state;

}