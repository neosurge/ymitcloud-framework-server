package com.ymit.module.system.api.logger;

import com.ymit.module.system.api.logger.dto.OperateLogCreateReqDTO;
import jakarta.validation.Valid;

/**
 * 操作日志 API 接口
 *
 * @author Y.S
 * @date 2024.05.22
 */
public interface OperateLogApi {
    /**
     * 创建操作日志
     *
     * @param createReqDTO 请求
     */
    void createOperateLog(@Valid OperateLogCreateReqDTO createReqDTO);
}
