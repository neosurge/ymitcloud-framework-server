package com.ymit.framework.idempotent.core.aop;

import com.ymit.framework.common.exception.ServiceException;
import com.ymit.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.ymit.framework.common.util.collection.CollectionUtils;
import com.ymit.framework.idempotent.core.annotation.Idempotent;
import com.ymit.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.ymit.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 拦截声明了 {@link Idempotent} 注解的方法，实现幂等操作
 *
 * @author Y.S
 * @date 2024.05.16
 */
@Aspect
public class IdempotentAspect {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(IdempotentAspect.class);
    private final IdempotentRedisDAO idempotentRedisDAO;
    private final Map<Class<? extends IdempotentKeyResolver>, IdempotentKeyResolver> keyResolvers;

    public IdempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        this.keyResolvers = CollectionUtils.convertMap(keyResolvers, IdempotentKeyResolver::getClass);
        this.idempotentRedisDAO = idempotentRedisDAO;
    }

    @Before("@annotation(idempotent)")
    public void beforePointCut(JoinPoint joinPoint, Idempotent idempotent) {
        // 获得 IdempotentKeyResolver
        IdempotentKeyResolver keyResolver = this.keyResolvers.get(idempotent.keyResolver());
        Assert.notNull(keyResolver, "找不到对应的 IdempotentKeyResolver");
        // 解析 Key
        String key = keyResolver.resolver(joinPoint, idempotent);

        // 锁定 Key。
        boolean success = this.idempotentRedisDAO.setIfAbsent(key, idempotent.timeout(), idempotent.timeUnit());
        // 锁定失败，抛出异常
        if (!success) {
            log.info("[beforePointCut][方法({}) 参数({}) 存在重复请求]", joinPoint.getSignature().toString(), joinPoint.getArgs());
            throw new ServiceException(GlobalErrorCodeConstants.REPEATED_REQUESTS.getCode(), idempotent.message());
        }
    }
}
