package com.ymitcloud.framework.file.config;

import com.ymitcloud.framework.file.core.client.FileClientFactory;
import com.ymitcloud.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 * @author 
 */
@AutoConfiguration
public class YmitcloudFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
