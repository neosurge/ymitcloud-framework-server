package com.ymitcloud.framework.mq.redis.config;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.ymitcloud.framework.mq.redis.core.RedisMQTemplate;
import com.ymitcloud.framework.mq.redis.core.interceptor.RedisMessageInterceptor;
import com.ymitcloud.framework.redis.config.YmitcloudRedisAutoConfiguration;

/**
 * Redis 消息队列 Producer 配置类
 *
 * @author
 */
@AutoConfiguration(after = YmitcloudRedisAutoConfiguration.class)
public class YmitcloudRedisMQProducerAutoConfiguration {

    @Bean
    RedisMQTemplate redisMQTemplate(StringRedisTemplate redisTemplate, List<RedisMessageInterceptor> interceptors) {
        RedisMQTemplate redisMQTemplate = new RedisMQTemplate(redisTemplate);
        // 添加拦截器
        interceptors.forEach(redisMQTemplate::addInterceptor);
        return redisMQTemplate;
    }

}
