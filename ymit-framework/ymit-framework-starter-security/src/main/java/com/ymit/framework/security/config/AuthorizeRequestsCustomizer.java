package com.ymit.framework.security.config;

import com.ymit.framework.web.config.WebProperties;
import jakarta.annotation.Resource;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * 自定义的 URL 的安全配置
 * <p>
 * 目的：每个 Maven Module 可以自定义规则！
 *
 * @author Y.S
 * @date 2024.05.16
 */
public abstract class AuthorizeRequestsCustomizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>, Ordered {
    private WebProperties webProperties;

    @Resource
    public void setWebProperties(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    protected String buildAdminApi(String url) {
        return this.webProperties.getAdminApi().getPrefix() + url;
    }

    protected String buildAppApi(String url) {
        return this.webProperties.getAppApi().getPrefix() + url;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
