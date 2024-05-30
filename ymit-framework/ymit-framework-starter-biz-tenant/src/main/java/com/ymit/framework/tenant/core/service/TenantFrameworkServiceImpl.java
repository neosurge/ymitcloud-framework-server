package com.ymit.framework.tenant.core.service;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ymit.framework.common.exception.ServiceException;
import com.ymit.framework.common.util.cache.CacheUtils;
import com.ymit.module.system.api.tenant.TenantApi;
import org.springframework.lang.NonNull;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Tenant 框架 Service 实现类
 *
 * @author Y.S
 * @date 2024.05.19
 */
public class TenantFrameworkServiceImpl implements TenantFrameworkService {
    private static final ServiceException SERVICE_EXCEPTION_NULL = new ServiceException();
    private final TenantApi tenantApi;
    /**
     * 针对 {@link #getTenantIds()} 的缓存
     */
    private final LoadingCache<Object, List<Long>> getTenantIdsCache = CacheUtils.buildAsyncReloadingCache(
            Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<Object, List<Long>>() {
                @Override
                @NonNull
                public List<Long> load(@NonNull Object key) {
                    return TenantFrameworkServiceImpl.this.tenantApi.getTenantIdList();
                }
            });
    /**
     * 针对 {@link #validTenant(Long)} 的缓存
     */
    private final LoadingCache<Long, ServiceException> validTenantCache = CacheUtils.buildAsyncReloadingCache(
            Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<Long, ServiceException>() {
                @Override
                @NonNull
                public ServiceException load(@NonNull Long id) {
                    try {
                        TenantFrameworkServiceImpl.this.tenantApi.validateTenant(id);
                        return SERVICE_EXCEPTION_NULL;
                    } catch (ServiceException ex) {
                        return ex;
                    }
                }
            });

    public TenantFrameworkServiceImpl(TenantApi tenantApi) {
        this.tenantApi = tenantApi;
    }

    @Override
    public List<Long> getTenantIds() throws ExecutionException {
        return this.getTenantIdsCache.get(Boolean.TRUE);
    }

    @Override
    public void validTenant(Long id) {
        ServiceException serviceException = this.validTenantCache.getUnchecked(id);
        if (serviceException != SERVICE_EXCEPTION_NULL) {
            throw serviceException;
        }
    }
}
