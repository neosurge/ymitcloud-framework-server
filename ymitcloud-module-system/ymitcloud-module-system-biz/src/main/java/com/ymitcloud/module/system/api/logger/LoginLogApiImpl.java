package com.ymitcloud.module.system.api.logger;

import com.ymitcloud.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.ymitcloud.module.system.service.logger.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 登录日志的 API 实现类
 *

 * @author 

 */
@Service
@Validated
public class LoginLogApiImpl implements LoginLogApi {

    @Resource
    private LoginLogService loginLogService;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        loginLogService.createLoginLog(reqDTO);
    }

}
