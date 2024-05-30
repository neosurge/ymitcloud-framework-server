package com.ymit.framework.idempotent.core.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 幂等 Redis DAO
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class IdempotentRedisDAO {

    /**
     * 幂等操作
     * <p>
     * KEY 格式：idempotent:%s // 参数为 uuid
     * VALUE 格式：String
     * 过期时间：不固定
     */
    private static final String IDEMPOTENT = "idempotent:%s";
    private final StringRedisTemplate redisTemplate;

    public IdempotentRedisDAO(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static String formatKey(String key) {
        return String.format(IDEMPOTENT, key);
    }

    public StringRedisTemplate getRedisTemplate() {
        return this.redisTemplate;
    }

    public Boolean setIfAbsent(String key, long timeout, TimeUnit timeUnit) {
        String redisKey = formatKey(key);
        return this.redisTemplate.opsForValue().setIfAbsent(redisKey, "", timeout, timeUnit);
    }
}
