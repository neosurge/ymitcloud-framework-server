package com.ymit.framework.idempotent.config;

import com.ymit.framework.idempotent.core.aop.IdempotentAspect;
import com.ymit.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.ymit.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.ymit.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.ymit.framework.idempotent.core.redis.IdempotentRedisDAO;
import com.ymit.framework.redis.config.YmitRedisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * 幂等配置类，在redis注册完成后执行
 *
 * @author Y.S
 * @date 2024.05.15
 */
@AutoConfiguration(after = YmitRedisAutoConfiguration.class)
public class YmitIdempotentConfiguration {
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
