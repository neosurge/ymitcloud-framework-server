package com.ymitcloud.framework.sms.config;

import com.ymitcloud.framework.sms.core.client.SmsClientFactory;
import com.ymitcloud.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 短信配置类
 *
 * @author 
 */
@AutoConfiguration
public class YmitcloudSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}
