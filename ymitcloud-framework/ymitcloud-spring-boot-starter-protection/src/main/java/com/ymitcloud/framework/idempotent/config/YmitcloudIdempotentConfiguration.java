package com.ymitcloud.framework.idempotent.config;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.ymitcloud.framework.idempotent.core.aop.IdempotentAspect;
import com.ymitcloud.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.ymitcloud.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.ymitcloud.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.ymitcloud.framework.idempotent.core.redis.IdempotentRedisDAO;
import com.ymitcloud.framework.redis.config.YmitcloudRedisAutoConfiguration;

@AutoConfiguration(after = YmitcloudRedisAutoConfiguration.class)
public class YmitcloudIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
