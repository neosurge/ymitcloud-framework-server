package com.ymitcloud.module.infra.api.logger;

import com.ymitcloud.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;

import jakarta.validation.Valid;

/**
 * API 访问日志的 API 接口
 *

 * @author 云码源码

 */
public interface ApiAccessLogApi {

    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     */
    void createApiAccessLog(@Valid ApiAccessLogCreateReqDTO createDTO);

}
