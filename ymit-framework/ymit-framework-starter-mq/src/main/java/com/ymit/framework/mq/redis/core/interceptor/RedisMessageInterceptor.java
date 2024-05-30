package com.ymit.framework.mq.redis.core.interceptor;

import com.ymit.framework.mq.redis.core.message.AbstractRedisMessage;

/**
 * {@link AbstractRedisMessage} 消息拦截器
 * <p>
 * 通过拦截器，作为插件机制，实现拓展。
 * <p>
 * 例如说，多租户场景下的 MQ 消息处理
 *
 * @author Y.S
 * @date 2024.05.17
 */
public interface RedisMessageInterceptor {
    void sendMessageBefore(AbstractRedisMessage message);

    void sendMessageAfter(AbstractRedisMessage message);

    void consumeMessageBefore(AbstractRedisMessage message);

    void consumeMessageAfter(AbstractRedisMessage message);
}
