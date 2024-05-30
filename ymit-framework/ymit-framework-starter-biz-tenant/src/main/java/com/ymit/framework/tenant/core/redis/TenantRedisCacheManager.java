package com.ymit.framework.tenant.core.redis;

import com.ymit.framework.redis.core.TimeoutRedisCacheManager;
import com.ymit.framework.tenant.core.context.TenantContextHolder;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.NonNull;

/**
 * 多租户的 {@link RedisCacheManager} 实现类
 * <p>
 * 操作指定 name 的 {@link Cache} 时，自动拼接租户后缀，格式为 name + ":" + tenantId + 后缀
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class TenantRedisCacheManager extends TimeoutRedisCacheManager {
    public TenantRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    public Cache getCache(@NonNull String name) {
        // 如果开启多租户，则 name 拼接租户后缀
        if (!TenantContextHolder.isIgnore() && TenantContextHolder.getTenantId() != null) {
            name = name + ":" + TenantContextHolder.getTenantId();
        }
        // 继续基于父方法
        return super.getCache(name);
    }
}
