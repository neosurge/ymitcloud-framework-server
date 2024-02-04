package com.ymitcloud.module.report.framework.security.config;

import com.ymitcloud.framework.security.config.AuthorizeRequestsCustomizer;
import com.ymitcloud.module.system.api.oauth2.OAuth2TokenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import jakarta.annotation.Resource;

/**
 * Report 模块的 Security 配置
 */
@Configuration("reportSecurityConfiguration")
public class SecurityConfiguration {

    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Bean("reportAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                registry.requestMatchers("/jmreport/**").permitAll(); // 积木报表
                registry.requestMatchers("/ureport/**").permitAll(); // UReport 报表
            }
        };
    }

    /**
     * 创建 UReportFilter 过滤器，响应 header 设置 token
     */
    /*@Bean
    public FilterRegistrationBean<UReportFilter> uReportFilterFilterRegistrationBean() {
        FilterRegistrationBean<UReportFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UReportFilter(oauth2TokenApi));
        registrationBean.setOrder(WebFilterOrderEnum.TRACE_FILTER);
        return registrationBean;
    }*/

}
