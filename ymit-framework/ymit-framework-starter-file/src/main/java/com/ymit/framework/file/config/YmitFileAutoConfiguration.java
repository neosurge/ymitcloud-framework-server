package com.ymit.framework.file.config;

import com.ymit.framework.file.core.client.FileClientFactory;
import com.ymit.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 * @author Y.S
 * @date 2024.05.24
 */
@AutoConfiguration
public class YmitFileAutoConfiguration {
    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }
}
