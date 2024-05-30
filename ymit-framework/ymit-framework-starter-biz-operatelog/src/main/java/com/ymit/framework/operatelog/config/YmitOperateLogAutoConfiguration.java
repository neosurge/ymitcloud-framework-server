package com.ymit.framework.operatelog.config;

import com.ymit.framework.operatelog.core.aop.OperateLogAspect;
import com.ymit.framework.operatelog.core.service.OperateLogFrameworkService;
import com.ymit.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.ymit.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 日志记录配置类
 *
 * @author Y.S
 * @date 2024.05.23
 */
@AutoConfiguration
public class YmitOperateLogAutoConfiguration {
    @Bean
    public OperateLogAspect operateLogAspect(OperateLogFrameworkService operateLogFrameworkService) {
        return new OperateLogAspect(operateLogFrameworkService);
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }
}
