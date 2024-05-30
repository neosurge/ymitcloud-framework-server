package com.ymit.module.system.api.logger;

import com.ymit.module.system.api.logger.dto.LoginLogCreateReqDTO;
import jakarta.validation.Valid;

/**
 * 登录日志的 API 接口
 *
 * @author Y.S
 * @date 2024.05.22
 */
public interface LoginLogApi {
    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(@Valid LoginLogCreateReqDTO reqDTO);
}
