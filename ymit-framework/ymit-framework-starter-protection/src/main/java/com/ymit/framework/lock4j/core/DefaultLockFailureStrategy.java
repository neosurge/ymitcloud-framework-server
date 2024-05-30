package com.ymit.framework.lock4j.core;

import com.baomidou.lock.LockFailureStrategy;
import com.ymit.framework.common.exception.ServiceException;
import com.ymit.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.slf4j.Logger;

import java.lang.reflect.Method;

/**
 * 自定义获取锁失败策略，抛出 {@link ServiceException} 异常
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class DefaultLockFailureStrategy implements LockFailureStrategy {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DefaultLockFailureStrategy.class);

    @Override
    public void onLockFailure(String key, Method method, Object[] arguments) {
        log.debug("[onLockFailure][线程:{} 获取锁失败，key:{} 获取失败:{} ]", Thread.currentThread().getName(), key, arguments);
        throw new ServiceException(GlobalErrorCodeConstants.LOCKED);
    }
}
