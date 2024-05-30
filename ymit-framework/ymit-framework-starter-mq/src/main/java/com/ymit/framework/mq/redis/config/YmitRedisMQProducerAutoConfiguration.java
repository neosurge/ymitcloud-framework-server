package com.ymit.framework.mq.redis.config;

import com.ymit.framework.mq.redis.core.RedisMQTemplate;
import com.ymit.framework.mq.redis.core.interceptor.RedisMessageInterceptor;
import com.ymit.framework.redis.config.YmitRedisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * Redis 消息队列 Producer 配置类
 *
 * @author Y.S
 * @date 2024.05.17
 */
@AutoConfiguration(after = YmitRedisAutoConfiguration.class)
public class YmitRedisMQProducerAutoConfiguration {
    @Bean
    RedisMQTemplate redisMQTemplate(StringRedisTemplate redisTemplate, List<RedisMessageInterceptor> interceptors) {
        RedisMQTemplate redisMQTemplate = new RedisMQTemplate(redisTemplate);
        // 添加拦截器
        interceptors.forEach(redisMQTemplate::addInterceptor);
        return redisMQTemplate;
    }
}
