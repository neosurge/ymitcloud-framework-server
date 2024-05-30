package com.ymit.module.system.controller.admin.oauth2.vo.client;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理后台 - OAuth2 客户端 Response VO
 */
@Data
public class OAuth2ClientRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 客户端编号
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String secret;
    /**
     * 应用名
     */
    private String name;
    /**
     * 应用图标
     */
    private String logo;
    /**
     * 应用描述
     */
    private String description;
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    private Integer status;
    /**
     * 访问令牌的有效期
     */
    private Integer accessTokenValiditySeconds;
    /**
     * 刷新令牌的有效期
     */
    private Integer refreshTokenValiditySeconds;
    /**
     * 可重定向的 URI 地址
     */
    private List<String> redirectUris;
    /**
     * 授权类型，参见 OAuth2GrantTypeEnum 枚举
     */
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
     * 创建时间
     */
    private LocalDateTime createTime;

}
