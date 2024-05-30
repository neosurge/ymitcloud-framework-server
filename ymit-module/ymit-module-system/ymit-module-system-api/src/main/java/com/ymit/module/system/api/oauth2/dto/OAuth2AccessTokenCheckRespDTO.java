package com.ymit.module.system.api.oauth2.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * OAuth2.0 访问令牌的校验 Response DTO
 *
 * @author Y.S
 * @date 2024.05.19
 */
public class OAuth2AccessTokenCheckRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 租户编号
     */
    private Long tenantId;
    /**
     * 授权范围的数组
     */
    private List<String> scopes;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<String> getScopes() {
        return this.scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
