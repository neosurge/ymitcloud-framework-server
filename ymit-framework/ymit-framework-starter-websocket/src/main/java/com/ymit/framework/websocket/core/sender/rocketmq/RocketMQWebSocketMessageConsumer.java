package com.ymit.framework.websocket.core.sender.rocketmq;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * {@link RocketMQWebSocketMessage} 广播消息的消费者，真正把消息发送出去
 *
 * @author Y.S
 * @date 2024.05.27
 */
@RocketMQMessageListener( // 重点：添加 @RocketMQMessageListener 注解，声明消费的 topic
        topic = "${ymit.websocket.sender-rocketmq.topic}", consumerGroup = "${ymit.websocket.sender-rocketmq.consumer-group}", messageModel = MessageModel.BROADCASTING // 设置为广播模式，保证每个实例都能收到消息
)
public class RocketMQWebSocketMessageConsumer implements RocketMQListener<RocketMQWebSocketMessage> {
    private final RocketMQWebSocketMessageSender rocketMQWebSocketMessageSender;

    public RocketMQWebSocketMessageConsumer(RocketMQWebSocketMessageSender rocketMQWebSocketMessageSender) {
        this.rocketMQWebSocketMessageSender = rocketMQWebSocketMessageSender;
    }

    @Override
    public void onMessage(RocketMQWebSocketMessage message) {
        this.rocketMQWebSocketMessageSender.send(message.getSessionId(), message.getUserType(), message.getUserId(), message.getMessageType(), message.getMessageContent());
    }
}
