package com.ymit.module.infra.api.logger;

import com.ymit.module.infra.api.logger.dto.ApiErrorLogDTO;
import jakarta.validation.Valid;

/**
 * API 错误日志的 API 接口
 *
 * @author Y.S
 * @date 2024.05.16
 */
public interface ApiErrorLogApi {
    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLog(@Valid ApiErrorLogDTO createDTO);
}
