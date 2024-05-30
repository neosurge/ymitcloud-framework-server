package com.ymit.framework.mq.redis.core.stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymit.framework.mq.redis.core.message.AbstractRedisMessage;

/**
 * Redis Stream Message 抽象类
 *
 * @author Y.S
 * @date 2024.05.17
 */
public abstract class AbstractRedisStreamMessage extends AbstractRedisMessage {
    /**
     * 获得 Redis Stream Key，默认使用类名
     *
     * @return Channel
     */
    @JsonIgnore // 避免序列化
    public String getStreamKey() {
        return this.getClass().getSimpleName();
    }
}
