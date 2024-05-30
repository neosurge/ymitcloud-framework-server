package com.ymit.framework.mq.rabbitmq.config;

import cn.hutool.core.util.ReflectUtil;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.lang.reflect.Field;

/**
 * RabbitMQ 消息队列配置类
 *
 * @author Y.S
 * @date 2024.05.17
 */
@AutoConfiguration
@ConditionalOnClass(name = "org.springframework.amqp.rabbit.core.RabbitTemplate")
public class YmitRabbitMQAutoConfiguration {
    static {
        // 强制设置 SerializationUtils 的 TRUST_ALL 为 true，避免 RabbitMQ Consumer 反序列化消息报错
        // 为什么不通过设置 spring.amqp.deserialization.trust.all 呢？因为可能在 SerializationUtils static 初始化后
        Field trustAllField = ReflectUtil.getField(SerializationUtils.class, "TRUST_ALL");
        ReflectUtil.removeFinalModify(trustAllField);
        ReflectUtil.setFieldValue(SerializationUtils.class, trustAllField, true);
    }
}
