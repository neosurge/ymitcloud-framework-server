package com.ymit.framework.apilog.config;

import com.ymit.framework.apilog.core.filter.ApiAccessLogFilter;
import com.ymit.framework.apilog.core.service.ApiAccessLogFrameworkService;
import com.ymit.framework.apilog.core.service.ApiAccessLogFrameworkServiceImpl;
import com.ymit.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.ymit.framework.apilog.core.service.ApiErrorLogFrameworkServiceImpl;
import com.ymit.framework.common.enums.WebFilterOrderEnum;
import com.ymit.framework.web.config.WebProperties;
import com.ymit.framework.web.config.YmitWebAutoConfiguration;
import com.ymit.module.infra.api.logger.ApiAccessLogApi;
import com.ymit.module.infra.api.logger.ApiErrorLogApi;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * API 访问日志自动配置
 *
 * @author Y.S
 * @date 2024.05.15
 */
@AutoConfiguration(after = YmitWebAutoConfiguration.class)
public class YmitApiLogAutoConfiguration {

    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     *
     * @hidden 允许使用 ymit.access-log.enable=false 禁用访问日志
     */
    @Bean
    @ConditionalOnProperty(prefix = "ymit.access-log", value = "enable", matchIfMissing = true)
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }
}
