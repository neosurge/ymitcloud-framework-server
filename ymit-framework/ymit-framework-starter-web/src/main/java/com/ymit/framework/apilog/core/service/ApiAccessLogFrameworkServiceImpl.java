package com.ymit.framework.apilog.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.ymit.module.infra.api.logger.ApiAccessLogApi;
import com.ymit.module.infra.api.logger.dto.ApiAccessLogDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * API 访问日志 Framework Service 实现类
 * <p>
 * 基于 {@link ApiAccessLogApi} 服务，记录访问日志
 *
 * @author Y.S
 * @date 2024.05.15
 */
@Component
public class ApiAccessLogFrameworkServiceImpl implements ApiAccessLogFrameworkService {

    private final ApiAccessLogApi apiAccessLogApi;

    public ApiAccessLogFrameworkServiceImpl(ApiAccessLogApi apiAccessLogApi) {
        this.apiAccessLogApi = apiAccessLogApi;
    }

    @Override
    @Async
    public void createApiAccessLog(ApiAccessLog apiAccessLog) {
        ApiAccessLogDTO reqDTO = BeanUtil.copyProperties(apiAccessLog, ApiAccessLogDTO.class);
        this.apiAccessLogApi.createApiAccessLog(reqDTO);
    }
}
