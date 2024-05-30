package com.ymit.framework.errorcode.config;

import com.ymit.framework.errorcode.core.generator.ErrorCodeAutoGenerator;
import com.ymit.framework.errorcode.core.generator.ErrorCodeAutoGeneratorImpl;
import com.ymit.framework.errorcode.core.loader.ErrorCodeLoader;
import com.ymit.framework.errorcode.core.loader.ErrorCodeLoaderImpl;
import com.ymit.module.system.api.errorcode.ErrorCodeApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 错误码配置类
 *
 * @author Y.S
 * @date 2024.05.23
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "ymit.error-code", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(ErrorCodeProperties.class)
@EnableScheduling // 开启调度任务的功能，因为 ErrorCodeRemoteLoader 通过定时刷新错误码
public class YmitErrorCodeAutoConfiguration {
    @Bean
    public ErrorCodeAutoGenerator errorCodeAutoGenerator(@Value("${spring.application.name}") String applicationName, ErrorCodeProperties errorCodeProperties, ErrorCodeApi errorCodeApi) {
        return new ErrorCodeAutoGeneratorImpl(applicationName, errorCodeProperties.getConstantsClassList(), errorCodeApi);
    }

    @Bean
    public ErrorCodeLoader errorCodeLoader(@Value("${spring.application.name}") String applicationName, ErrorCodeApi errorCodeApi) {
        return new ErrorCodeLoaderImpl(applicationName, errorCodeApi);
    }
}
