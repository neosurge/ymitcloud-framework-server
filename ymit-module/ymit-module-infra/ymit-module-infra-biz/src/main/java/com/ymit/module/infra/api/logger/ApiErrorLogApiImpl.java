package com.ymit.module.infra.api.logger;


import com.ymit.module.infra.api.logger.dto.ApiErrorLogDTO;
import com.ymit.module.infra.service.logger.ApiErrorLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * API 访问日志的 API 接口
 *
 * @author
 */
@Service
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public void createApiErrorLog(ApiErrorLogDTO createDTO) {
        this.apiErrorLogService.createApiErrorLog(createDTO);
    }

}
