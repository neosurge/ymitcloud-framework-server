package com.ymit.framework.sms.config;

import com.ymit.framework.sms.core.client.SmsClientFactory;
import com.ymit.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 短信配置类
 *
 * @author Y.S
 * @date 2024.05.23
 */
@AutoConfiguration
public class YmitSmsAutoConfiguration {
    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }
}
