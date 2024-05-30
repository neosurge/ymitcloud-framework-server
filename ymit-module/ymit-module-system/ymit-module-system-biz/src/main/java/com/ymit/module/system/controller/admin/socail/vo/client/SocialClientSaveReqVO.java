package com.ymit.module.system.controller.admin.socail.vo.client;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.enums.UserTypeEnum;
import com.ymit.framework.common.validation.InEnum;
import com.ymit.module.system.enums.social.SocialTypeEnum;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * 管理后台 - 社交客户端创建/修改 Request VO
 */
@Data
public class SocialClientSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 应用名
     */
    @NotNull(message = "应用名不能为空")
    private String name;
    /**
     * 社交平台的类型
     */
    @NotNull(message = "社交平台的类型不能为空")
    @InEnum(SocialTypeEnum.class)
    private Integer socialType;
    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    @InEnum(UserTypeEnum.class)
    private Integer userType;
    /**
     * 客户端编号
     */
    @NotNull(message = "客户端编号不能为空")
    private String clientId;
    /**
     * 客户端密钥
     */
    @NotNull(message = "客户端密钥不能为空")
    private String clientSecret;
    /**
     * 授权方的网页应用编号
     */
    private String agentId;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @AssertTrue(message = "agentId 不能为空")
    @JsonIgnore
    public boolean isAgentIdValid() {
        // 如果是企业微信，必须填写 agentId 属性
        return !Objects.equals(this.socialType, SocialTypeEnum.WECHAT_ENTERPRISE.getType()) || !StrUtil.isEmpty(this.agentId);
    }

}
