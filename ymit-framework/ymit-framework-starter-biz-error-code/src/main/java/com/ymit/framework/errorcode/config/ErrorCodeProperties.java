package com.ymit.framework.errorcode.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 错误码的配置属性类
 *
 * @author Y.S
 * @date 2024.05.23
 */
@ConfigurationProperties("ymit.error-code")
@Validated
public class ErrorCodeProperties {
    /**
     * 是否开启
     */
    private Boolean enable = true;
    /**
     * 错误码枚举类
     */
    @NotNull(message = "错误码枚举类不能为空")
    private List<String> constantsClassList;

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<String> getConstantsClassList() {
        return this.constantsClassList;
    }

    public void setConstantsClassList(List<String> constantsClassList) {
        this.constantsClassList = constantsClassList;
    }
}
