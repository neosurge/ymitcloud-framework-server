package com.ymit.module.system.api.oauth2.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OAuth2.0 访问令牌的信息 Response DTO
 *
 * @author Y.S
 * @date 2024.05.19
 */
public class OAuth2AccessTokenRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

    public String getAccessToken() {
        return this.accessToken;
    }

    public OAuth2AccessTokenRespDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public OAuth2AccessTokenRespDTO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public OAuth2AccessTokenRespDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public OAuth2AccessTokenRespDTO setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public LocalDateTime getExpiresTime() {
        return this.expiresTime;
    }

    public OAuth2AccessTokenRespDTO setExpiresTime(LocalDateTime expiresTime) {
        this.expiresTime = expiresTime;
        return this;
    }
}
