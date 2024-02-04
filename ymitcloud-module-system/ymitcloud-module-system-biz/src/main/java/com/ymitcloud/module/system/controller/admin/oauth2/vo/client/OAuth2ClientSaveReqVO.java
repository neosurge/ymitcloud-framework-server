package com.ymitcloud.module.system.controller.admin.oauth2.vo.client;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.ymitcloud.framework.common.util.json.JsonUtils;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - OAuth2 客户端创建/修改 Request VO
 */
@Data
public class OAuth2ClientSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 客户端编号
     */
    @NotNull(message = "客户端编号不能为空")
    private String clientId;
    /**
     * 客户端密钥
     */
    @NotNull(message = "客户端密钥不能为空")
    private String secret;
    /**
     * 应用名
     */
    @NotNull(message = "应用名不能为空")
    private String name;
    /**
     * 应用图标
     */
    @NotNull(message = "应用图标不能为空")
    @URL(message = "应用图标的地址不正确")
    private String logo;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 访问令牌的有效期
     */
    @NotNull(message = "访问令牌的有效期不能为空")
    private Integer accessTokenValiditySeconds;
    /**
     * 刷新令牌的有效期
     */
    @NotNull(message = "刷新令牌的有效期不能为空")
    private Integer refreshTokenValiditySeconds;
    /**
     * 可重定向的 URI 地址
     */
    @NotNull(message = "可重定向的 URI 地址不能为空")
    private List<@NotEmpty(message = "重定向的 URI 不能为空") @URL(message = "重定向的 URI 格式不正确") String> redirectUris;
    /**
     * 授权类型，参见 OAuth2GrantTypeEnum 枚举
     */
    @NotNull(message = "授权类型不能为空")
    private List<String> authorizedGrantTypes;
    /**
     * 授权范围
     */
    private List<String> scopes;
    /**
     * 自动通过的授权范围
     */
    private List<String> autoApproveScopes;
    /**
     * 权限
     */
    private List<String> authorities;
    /**
     * 资源
     */
    private List<String> resourceIds;
    /**
     * 附加信息
     */
    private String additionalInformation;

    /**
     * 附加信息必须是 JSON 格式
     * 
     * @return
     */
    public boolean isAdditionalInformationJson() {
        return StrUtil.isEmpty(additionalInformation) || JsonUtils.isJson(additionalInformation);
    }

}
