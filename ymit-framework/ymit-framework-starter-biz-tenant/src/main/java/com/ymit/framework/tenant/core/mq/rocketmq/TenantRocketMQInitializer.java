package com.ymit.framework.tenant.core.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
import org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

/**
 * 多租户的 RocketMQ 初始化器
 *
 * @author Y.S
 * @date 2024.05.19
 */
public class TenantRocketMQInitializer implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof DefaultRocketMQListenerContainer) {
            DefaultRocketMQListenerContainer container = (DefaultRocketMQListenerContainer) bean;
            this.initTenantConsumer(container.getConsumer());
        } else if (bean instanceof RocketMQTemplate) {
            RocketMQTemplate template = (RocketMQTemplate) bean;
            this.initTenantProducer(template.getProducer());
        }
        return bean;
    }

    @SuppressWarnings("deprecation")
    private void initTenantProducer(DefaultMQProducer producer) {
        if (producer == null) {
            return;
        }
        //在未发布新版本之前，未提供新的方法 https://github.com/apache/rocketmq/issues/7461
        DefaultMQProducerImpl producerImpl = producer.getDefaultMQProducerImpl();
        if (producerImpl == null) {
            return;
        }
        producerImpl.registerSendMessageHook(new TenantRocketMQSendMessageHook());
    }

    @SuppressWarnings("deprecation")
    private void initTenantConsumer(DefaultMQPushConsumer consumer) {
        if (consumer == null) {
            return;
        }
        //在未发布新版本之前，未提供新的方法 https://github.com/apache/rocketmq/issues/7461
        DefaultMQPushConsumerImpl consumerImpl = consumer.getDefaultMQPushConsumerImpl();
        if (consumerImpl == null) {
            return;
        }
        consumerImpl.registerConsumeMessageHook(new TenantRocketMQConsumeMessageHook());
    }
}
