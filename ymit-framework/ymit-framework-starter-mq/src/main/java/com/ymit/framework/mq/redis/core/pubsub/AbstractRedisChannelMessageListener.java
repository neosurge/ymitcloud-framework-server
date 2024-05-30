package com.ymit.framework.mq.redis.core.pubsub;

import cn.hutool.core.util.TypeUtil;
import com.ymit.framework.common.util.json.JsonUtils;
import com.ymit.framework.mq.redis.core.RedisMQTemplate;
import com.ymit.framework.mq.redis.core.interceptor.RedisMessageInterceptor;
import com.ymit.framework.mq.redis.core.message.AbstractRedisMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Redis Pub/Sub 监听器抽象类，用于实现广播消费
 *
 * @param <T> 消息类型。一定要填写噢，不然会报错
 * @author Y.S
 * @date 2024.05.17
 */
public abstract class AbstractRedisChannelMessageListener<T extends AbstractRedisChannelMessage> implements MessageListener {
    /**
     * 消息类型
     */
    private final Class<T> messageType;
    /**
     * Redis Channel
     */
    private final String channel;
    /**
     * RedisMQTemplate
     */
    private RedisMQTemplate redisMQTemplate;

    protected AbstractRedisChannelMessageListener() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.messageType = this.getMessageClass();
        this.channel = this.messageType.getDeclaredConstructor().newInstance().getChannel();
    }

    public void setRedisMQTemplate(RedisMQTemplate redisMQTemplate) {
        this.redisMQTemplate = redisMQTemplate;
    }

    /**
     * 获得 Sub 订阅的 Redis Channel 通道
     *
     * @return channel
     */
    public final String getChannel() {
        return this.channel;
    }

    @Override
    public final void onMessage(Message message, byte[] bytes) {
        T messageObj = JsonUtils.parseObject(message.getBody(), this.messageType);
        try {
            this.consumeMessageBefore(messageObj);
            // 消费消息
            this.onMessage(messageObj);
        } finally {
            this.consumeMessageAfter(messageObj);
        }
    }

    /**
     * 处理消息
     *
     * @param message 消息
     */
    public abstract void onMessage(T message);

    /**
     * 通过解析类上的泛型，获得消息类型
     *
     * @return 消息类型
     */
    @SuppressWarnings("unchecked")
    private Class<T> getMessageClass() {
        Type type = TypeUtil.getTypeArgument(this.getClass(), 0);
        if (type == null) {
            throw new IllegalStateException(String.format("类型(%s) 需要设置消息类型", this.getClass().getName()));
        }
        return (Class<T>) type;
    }

    private void consumeMessageBefore(AbstractRedisMessage message) {
        assert this.redisMQTemplate != null;
        List<RedisMessageInterceptor> interceptors = this.redisMQTemplate.getInterceptors();
        // 正序
        interceptors.forEach(interceptor -> interceptor.consumeMessageBefore(message));
    }

    private void consumeMessageAfter(AbstractRedisMessage message) {
        assert this.redisMQTemplate != null;
        List<RedisMessageInterceptor> interceptors = this.redisMQTemplate.getInterceptors();
        // 倒序
        for (int i = interceptors.size() - 1; i >= 0; i--) {
            interceptors.get(i).consumeMessageAfter(message);
        }
    }
}
