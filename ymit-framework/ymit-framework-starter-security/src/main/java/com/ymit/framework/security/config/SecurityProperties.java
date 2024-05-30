package com.ymit.framework.security.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

/**
 * SecurityProperties 配置属性
 *
 * @author Y.S
 * @date 2024.05.16
 */
@ConfigurationProperties(prefix = "ymit.security")
@Validated
public class SecurityProperties {
    /**
     * HTTP 请求时，访问令牌的请求 Header
     */
    @NotEmpty(message = "Token Header 不能为空")
    private String tokenHeader = "Authorization";
    /**
     * HTTP 请求时，访问令牌的请求参数
     * <p>
     * 初始目的：解决 WebSocket 无法通过 header 传参，只能通过 token 参数拼接
     */
    @NotEmpty(message = "Token Parameter 不能为空")
    private String tokenParameter = "token";
    /**
     * mock 模式的开关
     */
    @NotNull(message = "mock 模式的开关不能为空")
    private Boolean mockEnable = false;
    /**
     * mock 模式的密钥
     * 一定要配置密钥，保证安全性
     */
    @NotEmpty(message = "mock 模式的密钥不能为空") // 这里设置了一个默认值，因为实际上只有 mockEnable 为 true 时才需要配置。
    private String mockSecret = "test";
    /**
     * 免登录的 URL 列表
     */
    private List<String> permitAllUrls = Collections.emptyList();
    /**
     * PasswordEncoder 加密复杂度，越高开销越大
     */
    private Integer passwordEncoderLength = 4;

    public String getTokenHeader() {
        return this.tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenParameter() {
        return this.tokenParameter;
    }

    public void setTokenParameter(String tokenParameter) {
        this.tokenParameter = tokenParameter;
    }

    public Boolean getMockEnable() {
        return this.mockEnable;
    }

    public void setMockEnable(Boolean mockEnable) {
        this.mockEnable = mockEnable;
    }

    public String getMockSecret() {
        return this.mockSecret;
    }

    public void setMockSecret(String mockSecret) {
        this.mockSecret = mockSecret;
    }

    public List<String> getPermitAllUrls() {
        return this.permitAllUrls;
    }

    public void setPermitAllUrls(List<String> permitAllUrls) {
        this.permitAllUrls = permitAllUrls;
    }

    public Integer getPasswordEncoderLength() {
        return this.passwordEncoderLength;
    }

    public void setPasswordEncoderLength(Integer passwordEncoderLength) {
        this.passwordEncoderLength = passwordEncoderLength;
    }
}
