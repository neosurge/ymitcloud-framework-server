package com.ymit.framework.quartz.core.enums;

/**
 * Quartz Job Data 的 key 枚举
 *
 * @author Y.S
 * @date 2024.05.16
 */
public enum JobDataKeyEnum {
    /**
     * 任务Id
     */
    JOB_ID,
    /**
     * 任务名称
     */
    JOB_HANDLER_NAME,
    /**
     * 任务参数
     */
    JOB_HANDLER_PARAM,
    /**
     * 最大重试次数
     */
    JOB_RETRY_COUNT,
    /**
     * 每次重试间隔
     */
    JOB_RETRY_INTERVAL,
}
