package com.ymit.framework.sms.core.property;

import com.ymit.framework.sms.core.enums.SmsChannelEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * 短信渠道配置类
 *
 * @author Y.S
 * @date 2024.05.23
 */
@Validated
public class SmsChannelProperties {
    /**
     * 渠道编号
     */
    @NotNull(message = "短信渠道 ID 不能为空")
    private Long id;
    /**
     * 短信签名
     */
    @NotEmpty(message = "短信签名不能为空")
    private String signature;
    /**
     * 渠道编码
     * <p>
     * 枚举 {@link SmsChannelEnum}
     */
    @NotEmpty(message = "渠道编码不能为空")
    private String code;
    /**
     * 短信 API 的账号
     */
    @NotEmpty(message = "短信 API 的账号不能为空")
    private String apiKey;
    /**
     * 短信 API 的密钥
     */
    @NotEmpty(message = "短信 API 的密钥不能为空")
    private String apiSecret;
    /**
     * 短信发送回调 URL
     */
    private String callbackUrl;

    public Long getId() {
        return this.id;
    }

    public SmsChannelProperties setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSignature() {
        return this.signature;
    }

    public SmsChannelProperties setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public SmsChannelProperties setCode(String code) {
        this.code = code;
        return this;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public SmsChannelProperties setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return this.apiSecret;
    }

    public SmsChannelProperties setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }

    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public SmsChannelProperties setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }
}
