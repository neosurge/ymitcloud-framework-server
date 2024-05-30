package com.ymit.framework.websocket.core.sender.redis;

import com.ymit.framework.mq.redis.core.pubsub.AbstractRedisChannelMessageListener;

import java.lang.reflect.InvocationTargetException;

/**
 * {@link RedisWebSocketMessage} 广播消息的消费者，真正把消息发送出去
 *
 * @author Y.S
 * @date 2024.05.26
 */
public class RedisWebSocketMessageConsumer extends AbstractRedisChannelMessageListener<RedisWebSocketMessage> {

    private final RedisWebSocketMessageSender redisWebSocketMessageSender;

    public RedisWebSocketMessageConsumer(RedisWebSocketMessageSender redisWebSocketMessageSender) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super();
        this.redisWebSocketMessageSender = redisWebSocketMessageSender;
    }

    @Override
    public void onMessage(RedisWebSocketMessage message) {
        this.redisWebSocketMessageSender.send(message.getSessionId(), message.getUserType(), message.getUserId(), message.getMessageType(), message.getMessageContent());
    }
}
