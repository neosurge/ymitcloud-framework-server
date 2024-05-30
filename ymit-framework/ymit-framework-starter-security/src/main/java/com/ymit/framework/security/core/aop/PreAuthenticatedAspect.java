package com.ymit.framework.security.core.aop;

import com.ymit.framework.security.core.annotations.PreAuthenticated;
import com.ymit.framework.security.core.util.SecurityFrameworkUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static com.ymit.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static com.ymit.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 登录认证的切面
 *
 * @author Y.S
 * @date 2024.05.16
 */
@Aspect
public class PreAuthenticatedAspect {
    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (SecurityFrameworkUtils.getLoginUser() == null) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
