package com.ymit.framework.xss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymit.framework.common.enums.WebFilterOrderEnum;
import com.ymit.framework.xss.core.clean.JsoupXssCleaner;
import com.ymit.framework.xss.core.clean.XssCleaner;
import com.ymit.framework.xss.core.filter.XssFilter;
import com.ymit.framework.xss.core.json.XssStringJsonDeserializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.ymit.framework.web.config.YmitWebAutoConfiguration.createFilterBean;

/**
 * Xss 配置类
 *
 * @author Y.S
 * @date 2024.05.19
 */
@AutoConfiguration
@EnableConfigurationProperties(XssProperties.class)
@ConditionalOnProperty(prefix = "ymit.xss", name = "enable", havingValue = "true", matchIfMissing = true)
public class YmitXssAutoConfiguration implements WebMvcConfigurer {
    /**
     * Xss 清理者
     *
     * @return XssCleaner
     */
    @Bean
    @ConditionalOnMissingBean(XssCleaner.class)
    public XssCleaner xssCleaner() {
        return new JsoupXssCleaner();
    }

    /**
     * 注册 Jackson 的序列化器，用于处理 json 类型参数的 xss 过滤
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    @ConditionalOnMissingBean(name = "xssJacksonCustomizer")
    @ConditionalOnBean(ObjectMapper.class)
    @ConditionalOnProperty(value = "ymit.xss.enable", havingValue = "true")
    public Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer(XssCleaner xssCleaner) {
        // 在反序列化时进行 xss 过滤，可以替换使用 XssStringJsonSerializer，在序列化时进行处理
        return builder -> builder.deserializerByType(String.class, new XssStringJsonDeserializer(xssCleaner));
    }

    /**
     * 创建 XssFilter Bean，解决 Xss 安全问题
     */
    @Bean
    @ConditionalOnBean(XssCleaner.class)
    public FilterRegistrationBean<XssFilter> xssFilter(XssProperties properties, PathMatcher pathMatcher, XssCleaner xssCleaner) {
        return createFilterBean(new XssFilter(properties, pathMatcher, xssCleaner), WebFilterOrderEnum.XSS_FILTER);
    }
}
