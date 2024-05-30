package com.ymit.framework.websocket.config;

import com.ymit.framework.mq.redis.config.YmitRedisMQConsumerAutoConfiguration;
import com.ymit.framework.mq.redis.core.RedisMQTemplate;
import com.ymit.framework.websocket.core.handler.JsonWebSocketMessageHandler;
import com.ymit.framework.websocket.core.listener.WebSocketMessageListener;
import com.ymit.framework.websocket.core.security.LoginUserHandshakeInterceptor;
import com.ymit.framework.websocket.core.sender.kafka.KafkaWebSocketMessageConsumer;
import com.ymit.framework.websocket.core.sender.kafka.KafkaWebSocketMessageSender;
import com.ymit.framework.websocket.core.sender.local.LocalWebSocketMessageSender;
import com.ymit.framework.websocket.core.sender.rabbitmq.RabbitMQWebSocketMessageConsumer;
import com.ymit.framework.websocket.core.sender.rabbitmq.RabbitMQWebSocketMessageSender;
import com.ymit.framework.websocket.core.sender.redis.RedisWebSocketMessageConsumer;
import com.ymit.framework.websocket.core.sender.redis.RedisWebSocketMessageSender;
import com.ymit.framework.websocket.core.sender.rocketmq.RocketMQWebSocketMessageConsumer;
import com.ymit.framework.websocket.core.sender.rocketmq.RocketMQWebSocketMessageSender;
import com.ymit.framework.websocket.core.session.WebSocketSessionHandlerDecorator;
import com.ymit.framework.websocket.core.session.WebSocketSessionManager;
import com.ymit.framework.websocket.core.session.WebSocketSessionManagerImpl;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * WebSocket 自动配置
 *
 * @author Y.S
 * @date 2024.05.26
 * @hidden before YmitRedisMQConsumerAutoConfiguration 的原因是，需要保证 RedisWebSocketMessageConsumer 先创建，才能创建 RedisMessageListenerContainer
 */
@AutoConfiguration(before = YmitRedisMQConsumerAutoConfiguration.class)
@EnableWebSocket
@ConditionalOnProperty(prefix = "ymit.websocket", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(WebSocketProperties.class)
public class YmitWebSocketAutoConfiguration {

    @Bean
    public WebSocketConfigurer webSocketConfigurer(HandshakeInterceptor[] handshakeInterceptors, WebSocketHandler webSocketHandler, WebSocketProperties webSocketProperties) {
        return registry -> registry
                // 添加 WebSocketHandler
                .addHandler(webSocketHandler, webSocketProperties.getPath()).addInterceptors(handshakeInterceptors)
                // 允许跨域，否则前端连接会直接断开
                .setAllowedOriginPatterns("*");
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new LoginUserHandshakeInterceptor();
    }

    @Bean
    public WebSocketHandler webSocketHandler(WebSocketSessionManager sessionManager, List<? extends WebSocketMessageListener<?>> messageListeners) {
        // 1. 创建 JsonWebSocketMessageHandler 对象，处理消息
        JsonWebSocketMessageHandler messageHandler = new JsonWebSocketMessageHandler(messageListeners);
        // 2. 创建 WebSocketSessionHandlerDecorator 对象，处理连接
        return new WebSocketSessionHandlerDecorator(messageHandler, sessionManager);
    }

    @Bean
    public WebSocketSessionManager webSocketSessionManager() {
        return new WebSocketSessionManagerImpl();
    }

    // ==================== Sender 相关 ====================

    @Configuration
    @ConditionalOnProperty(prefix = "ymit.websocket", name = "sender-type", havingValue = "local", matchIfMissing = true)
    public class LocalWebSocketMessageSenderConfiguration {

        @Bean
        public LocalWebSocketMessageSender localWebSocketMessageSender(WebSocketSessionManager sessionManager) {
            return new LocalWebSocketMessageSender(sessionManager);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "ymit.websocket", name = "sender-type", havingValue = "redis", matchIfMissing = true)
    public class RedisWebSocketMessageSenderConfiguration {

        @Bean
        public RedisWebSocketMessageSender redisWebSocketMessageSender(WebSocketSessionManager sessionManager, RedisMQTemplate redisMQTemplate) {
            return new RedisWebSocketMessageSender(sessionManager, redisMQTemplate);
        }

        @Bean
        public RedisWebSocketMessageConsumer redisWebSocketMessageConsumer(RedisWebSocketMessageSender redisWebSocketMessageSender) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            return new RedisWebSocketMessageConsumer(redisWebSocketMessageSender);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "ymit.websocket", name = "sender-type", havingValue = "rocketmq", matchIfMissing = true)
    public class RocketMQWebSocketMessageSenderConfiguration {

        @Bean
        public RocketMQWebSocketMessageSender rocketMQWebSocketMessageSender(WebSocketSessionManager sessionManager, RocketMQTemplate rocketMQTemplate, @Value("${ymit.websocket.sender-rocketmq.topic}") String topic) {
            return new RocketMQWebSocketMessageSender(sessionManager, rocketMQTemplate, topic);
        }

        @Bean
        public RocketMQWebSocketMessageConsumer rocketMQWebSocketMessageConsumer(RocketMQWebSocketMessageSender rocketMQWebSocketMessageSender) {
            return new RocketMQWebSocketMessageConsumer(rocketMQWebSocketMessageSender);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "ymit.websocket", name = "sender-type", havingValue = "rabbitmq", matchIfMissing = true)
    public class RabbitMQWebSocketMessageSenderConfiguration {

        @Bean
        public RabbitMQWebSocketMessageSender rabbitMQWebSocketMessageSender(WebSocketSessionManager sessionManager, RabbitTemplate rabbitTemplate, TopicExchange websocketTopicExchange) {
            return new RabbitMQWebSocketMessageSender(sessionManager, rabbitTemplate, websocketTopicExchange);
        }

        @Bean
        public RabbitMQWebSocketMessageConsumer rabbitMQWebSocketMessageConsumer(RabbitMQWebSocketMessageSender rabbitMQWebSocketMessageSender) {
            return new RabbitMQWebSocketMessageConsumer(rabbitMQWebSocketMessageSender);
        }

        /**
         * 创建 Topic Exchange
         */
        @Bean
        public TopicExchange websocketTopicExchange(@Value("${ymit.websocket.sender-rabbitmq.exchange}") String exchange) {
            // durable: 是否持久化
            // exclusive: 是否排它
            return new TopicExchange(exchange, true, false);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "ymit.websocket", name = "sender-type", havingValue = "kafka", matchIfMissing = true)
    public class KafkaWebSocketMessageSenderConfiguration {

        @Bean
        public KafkaWebSocketMessageSender kafkaWebSocketMessageSender(WebSocketSessionManager sessionManager, KafkaTemplate<Object, Object> kafkaTemplate, @Value("${ymit.websocket.sender-kafka.topic}") String topic) {
            return new KafkaWebSocketMessageSender(sessionManager, kafkaTemplate, topic);
        }

        @Bean
        public KafkaWebSocketMessageConsumer kafkaWebSocketMessageConsumer(KafkaWebSocketMessageSender kafkaWebSocketMessageSender) {
            return new KafkaWebSocketMessageConsumer(kafkaWebSocketMessageSender);
        }

    }
}
