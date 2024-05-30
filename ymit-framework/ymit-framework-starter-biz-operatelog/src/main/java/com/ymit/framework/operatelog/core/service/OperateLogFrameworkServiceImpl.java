package com.ymit.framework.operatelog.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.ymit.module.system.api.logger.OperateLogApi;
import com.ymit.module.system.api.logger.dto.OperateLogCreateReqDTO;
import org.springframework.scheduling.annotation.Async;

/**
 * 操作日志 Framework Service 实现类
 * <p>
 * 基于 {@link OperateLogApi} 实现，记录操作日志
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {
    private final OperateLogApi operateLogApi;

    public OperateLogFrameworkServiceImpl(OperateLogApi operateLogApi) {
        this.operateLogApi = operateLogApi;
    }

    @Override
    @Async
    public void createOperateLog(OperateLog operateLog) {
        OperateLogCreateReqDTO reqDTO = BeanUtil.toBean(operateLog, OperateLogCreateReqDTO.class);
        this.operateLogApi.createOperateLog(reqDTO);
    }
}
