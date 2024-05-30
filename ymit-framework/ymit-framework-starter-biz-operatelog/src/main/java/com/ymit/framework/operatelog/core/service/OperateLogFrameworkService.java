package com.ymit.framework.operatelog.core.service;

/**
 * 操作日志 Framework Service 接口
 *
 * @author Y.S
 * @date 2024.05.23
 */
public interface OperateLogFrameworkService {
    /**
     * 记录操作日志
     *
     * @param operateLog 操作日志请求
     */
    void createOperateLog(OperateLog operateLog);
}
