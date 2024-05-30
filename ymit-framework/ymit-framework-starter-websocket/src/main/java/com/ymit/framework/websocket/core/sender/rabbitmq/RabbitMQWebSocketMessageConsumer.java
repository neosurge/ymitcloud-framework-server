package com.ymit.framework.websocket.core.sender.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;

/**
 * {@link RabbitMQWebSocketMessage} 广播消息的消费者，真正把消息发送出去
 *
 * @author Y.S
 * @date 2024.05.26
 */
@RabbitListener(bindings = @QueueBinding(value = @Queue(
        // 在 Queue 的名字上，使用 UUID 生成其后缀。这样，启动的 Consumer 的 Queue 不同，以达到广播消费的目的
        name = "${ymit.websocket.sender-rabbitmq.queue}" + "-" + "#{T(java.util.UUID).randomUUID()}",
        // Consumer 关闭时，该队列就可以被自动删除了
        autoDelete = "true"), exchange = @Exchange(name = "${ymit.websocket.sender-rabbitmq.exchange}", type = ExchangeTypes.TOPIC, declare = "false")))
public class RabbitMQWebSocketMessageConsumer {

    private final RabbitMQWebSocketMessageSender rabbitMQWebSocketMessageSender;

    public RabbitMQWebSocketMessageConsumer(RabbitMQWebSocketMessageSender rabbitMQWebSocketMessageSender) {
        this.rabbitMQWebSocketMessageSender = rabbitMQWebSocketMessageSender;
    }

    @RabbitHandler
    public void onMessage(RabbitMQWebSocketMessage message) {
        this.rabbitMQWebSocketMessageSender.send(message.getSessionId(), message.getUserType(), message.getUserId(), message.getMessageType(), message.getMessageContent());
    }
}
