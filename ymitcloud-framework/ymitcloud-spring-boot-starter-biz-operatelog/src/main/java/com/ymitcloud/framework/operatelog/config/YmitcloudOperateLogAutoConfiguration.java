package com.ymitcloud.framework.operatelog.config;

import com.ymitcloud.framework.operatelog.core.aop.OperateLogAspect;
import com.ymitcloud.framework.operatelog.core.service.OperateLogFrameworkService;
import com.ymitcloud.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.ymitcloud.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class YmitcloudOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
