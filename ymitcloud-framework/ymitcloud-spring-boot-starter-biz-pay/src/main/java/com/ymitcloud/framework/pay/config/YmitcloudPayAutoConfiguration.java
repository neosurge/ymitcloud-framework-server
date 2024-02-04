package com.ymitcloud.framework.pay.config;

import com.ymitcloud.framework.pay.core.client.PayClientFactory;
import com.ymitcloud.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 * @author 
 */
@AutoConfiguration
public class YmitcloudPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
