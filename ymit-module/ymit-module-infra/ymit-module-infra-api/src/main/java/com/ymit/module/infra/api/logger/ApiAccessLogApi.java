package com.ymit.module.infra.api.logger;

import com.ymit.module.infra.api.logger.dto.ApiAccessLogDTO;
import jakarta.validation.Valid;

/**
 * API 访问日志的 API 接口
 *
 * @author Y.S
 * @date 2024.05.16
 */
public interface ApiAccessLogApi {
    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     */
    void createApiAccessLog(@Valid ApiAccessLogDTO createDTO);
}
