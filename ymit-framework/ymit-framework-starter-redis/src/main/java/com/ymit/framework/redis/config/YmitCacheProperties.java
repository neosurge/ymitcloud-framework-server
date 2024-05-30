package com.ymit.framework.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Cache 配置项
 *
 * @author Y.S
 * @date 2024.05.15
 */
@ConfigurationProperties("ymit.cache")
@Validated
public class YmitCacheProperties {
    /**
     * {@link #redisScanBatchSize} 默认值
     */
    private static final Integer REDIS_SCAN_BATCH_SIZE_DEFAULT = 30;

    /**
     * redis scan 一次返回数量
     */
    private Integer redisScanBatchSize = REDIS_SCAN_BATCH_SIZE_DEFAULT;

    public Integer getRedisScanBatchSize() {
        return this.redisScanBatchSize;
    }

    public void setRedisScanBatchSize(Integer redisScanBatchSize) {
        this.redisScanBatchSize = redisScanBatchSize;
    }
}
