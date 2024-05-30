package com.ymit.framework.quartz.core.handler;

/**
 * 任务处理器
 *
 * @author Y.S
 * @date 2024.05.17
 */
public interface JobHandler {
    /**
     * 执行任务
     *
     * @param param 参数
     * @return 结果
     * @throws Exception 异常
     */
    String execute(String param) throws Exception;
}
