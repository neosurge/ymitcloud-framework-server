package com.ymitcloud.module.system.api.logger;

import com.ymitcloud.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.ymitcloud.module.system.service.logger.OperateLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 操作日志 API 实现类
 *

 * @author 云码源码

 */
@Service
@Validated
public class OperateLogApiImpl implements OperateLogApi {

    @Resource
    private OperateLogService operateLogService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

}
