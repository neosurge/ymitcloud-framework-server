package com.ymit.module.infra.api.logger;

import com.ymit.module.infra.api.logger.dto.ApiAccessLogDTO;
import com.ymit.module.infra.service.logger.ApiAccessLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * API 访问日志的 API 实现类
 *
 * @author 云码源码
 */
@Service
@Validated
public class ApiAccessLogApiImpl implements ApiAccessLogApi {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @Override
    public void createApiAccessLog(ApiAccessLogDTO createDTO) {
        this.apiAccessLogService.createApiAccessLog(createDTO);
    }
}
