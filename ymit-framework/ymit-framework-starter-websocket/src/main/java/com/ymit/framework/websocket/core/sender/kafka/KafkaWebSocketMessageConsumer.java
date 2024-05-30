package com.ymit.framework.websocket.core.sender.kafka;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * {@link KafkaWebSocketMessage} 广播消息的消费者，真正把消息发送出去
 *
 * @author Y.S
 * @date 2024.05.26
 */
public class KafkaWebSocketMessageConsumer {
    private final KafkaWebSocketMessageSender rabbitMQWebSocketMessageSender;

    public KafkaWebSocketMessageConsumer(KafkaWebSocketMessageSender rabbitMQWebSocketMessageSender) {
        this.rabbitMQWebSocketMessageSender = rabbitMQWebSocketMessageSender;
    }

    @RabbitHandler
    @KafkaListener(topics = "${ymit.websocket.sender-kafka.topic}",
            // 在 Group 上，使用 UUID 生成其后缀。这样，启动的 Consumer 的 Group 不同，以达到广播消费的目的
            groupId = "${ymit.websocket.sender-kafka.consumer-group}" + "-" + "#{T(java.util.UUID).randomUUID()}")
    public void onMessage(KafkaWebSocketMessage message) {
        this.rabbitMQWebSocketMessageSender.send(message.getSessionId(), message.getUserType(), message.getUserId(), message.getMessageType(), message.getMessageContent());
    }
}
