package com.ymitcloud.module.system.api.social.dto;

import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.system.enums.social.SocialTypeEnum;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 社交绑定 Request DTO，使用 code 授权码
 *

 * @author 云码源码

 */
@Data
public class SocialUserUnbindReqDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    /**
     * 用户类型
     */
    @InEnum(UserTypeEnum.class)
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /**
     * 社交平台的类型
     */
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer socialType;

    /**
     * 社交平台的 openid
     */
    @NotEmpty(message = "社交平台的 openid 不能为空")
    private String openid;

}