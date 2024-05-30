package com.ymit.framework.mq.redis.core;

import com.ymit.framework.common.util.json.JsonUtils;
import com.ymit.framework.mq.redis.core.interceptor.RedisMessageInterceptor;
import com.ymit.framework.mq.redis.core.message.AbstractRedisMessage;
import com.ymit.framework.mq.redis.core.pubsub.AbstractRedisChannelMessage;
import com.ymit.framework.mq.redis.core.stream.AbstractRedisStreamMessage;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis MQ 操作模板类
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class RedisMQTemplate {
    private final RedisTemplate<String, ?> redisTemplate;
    /**
     * 拦截器数组
     */
    private final List<RedisMessageInterceptor> interceptors = new ArrayList<>();

    public RedisMQTemplate(RedisTemplate<String, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate<String, ?> getRedisTemplate() {
        return this.redisTemplate;
    }

    public List<RedisMessageInterceptor> getInterceptors() {
        return this.interceptors;
    }

    /**
     * 发送 Redis 消息，基于 Redis pub/sub 实现
     *
     * @param message 消息
     */
    public <T extends AbstractRedisChannelMessage> void send(T message) {
        try {
            this.sendMessageBefore(message);
            // 发送消息
            this.redisTemplate.convertAndSend(message.getChannel(), JsonUtils.toJsonString(message));
        } finally {
            this.sendMessageAfter(message);
        }
    }

    /**
     * 发送 Redis 消息，基于 Redis Stream 实现
     *
     * @param message 消息
     * @return 消息记录的编号对象
     */
    public <T extends AbstractRedisStreamMessage> RecordId send(T message) {
        try {
            this.sendMessageBefore(message);
            // 发送消息
            return this.redisTemplate.opsForStream()
                    .add(StreamRecords.newRecord().ofObject(JsonUtils.toJsonString(message)) // 设置内容
                            .withStreamKey(message.getStreamKey())); // 设置 stream key
        } finally {
            this.sendMessageAfter(message);
        }
    }

    /**
     * 添加拦截器
     *
     * @param interceptor 拦截器
     */
    public void addInterceptor(RedisMessageInterceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    private void sendMessageBefore(AbstractRedisMessage message) {
        // 正序
        this.interceptors.forEach(interceptor -> interceptor.sendMessageBefore(message));
    }

    private void sendMessageAfter(AbstractRedisMessage message) {
        // 倒序
        for (int i = this.interceptors.size() - 1; i >= 0; i--) {
            this.interceptors.get(i).sendMessageAfter(message);
        }
    }
}
