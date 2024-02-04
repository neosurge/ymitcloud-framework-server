package com.ymitcloud.module.system.api.logger;

import com.ymitcloud.module.system.api.logger.dto.OperateLogCreateReqDTO;

import jakarta.validation.Valid;

/**
 * 操作日志 API 接口
 *

 * @author 

 */
public interface OperateLogApi {

    /**
     * 创建操作日志
     *
     * @param createReqDTO 请求
     */
    void createOperateLog(@Valid OperateLogCreateReqDTO createReqDTO);

}
