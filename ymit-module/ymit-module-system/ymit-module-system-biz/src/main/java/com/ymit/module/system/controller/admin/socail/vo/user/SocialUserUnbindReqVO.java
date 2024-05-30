package com.ymit.module.system.controller.admin.socail.vo.user;

import com.ymit.framework.common.validation.InEnum;
import com.ymit.module.system.enums.social.SocialTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 取消社交绑定 Request VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialUserUnbindReqVO {
    /**
     * 社交平台的类型，参见 UserSocialTypeEnum 枚举值
     */
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer type;
    /**
     * 社交用户的 openid
     */
    @NotEmpty(message = "社交用户的 openid 不能为空")
    private String openid;

}
