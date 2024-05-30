package com.ymit.framework.apilog.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.ymit.module.infra.api.logger.ApiErrorLogApi;
import com.ymit.module.infra.api.logger.dto.ApiErrorLogDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * API 错误日志 Framework Service 实现类
 * <p>
 * 基于 {@link ApiErrorLogApi} 服务，记录错误日志
 *
 * @author Y.S
 * @date 2024.05.15
 */
@Component
public class ApiErrorLogFrameworkServiceImpl implements ApiErrorLogFrameworkService {
    private final ApiErrorLogApi apiErrorLogApi;

    public ApiErrorLogFrameworkServiceImpl(ApiErrorLogApi apiErrorLogApi) {
        this.apiErrorLogApi = apiErrorLogApi;
    }

    @Override
    @Async
    public void createApiErrorLog(ApiErrorLog apiErrorLog) {
        ApiErrorLogDTO reqDTO = BeanUtil.copyProperties(apiErrorLog, ApiErrorLogDTO.class);
        this.apiErrorLogApi.createApiErrorLog(reqDTO);
    }
}
