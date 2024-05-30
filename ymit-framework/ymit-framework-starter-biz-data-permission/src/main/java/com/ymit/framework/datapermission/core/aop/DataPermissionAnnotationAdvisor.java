package com.ymit.framework.datapermission.core.aop;

import com.ymit.framework.datapermission.core.annotation.DataPermission;
import jakarta.annotation.Nonnull;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.Serial;

/**
 * {@link DataPermission} 注解的 Advisor 实现类
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class DataPermissionAnnotationAdvisor extends AbstractPointcutAdvisor {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Advice advice;
    private final Pointcut pointcut;

    public DataPermissionAnnotationAdvisor() {
        this.advice = new DataPermissionAnnotationInterceptor();
        this.pointcut = this.buildPointcut();
    }

    @Override
    @Nonnull
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    @Nonnull
    public Advice getAdvice() {
        return this.advice;
    }

    protected Pointcut buildPointcut() {
        Pointcut classPointcut = new AnnotationMatchingPointcut(DataPermission.class, true);
        Pointcut methodPointcut = new AnnotationMatchingPointcut(null, DataPermission.class, true);
        return new ComposablePointcut(classPointcut).union(methodPointcut);
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!super.equals(other)) {
            return false;
        }
        if (other instanceof DataPermissionAnnotationAdvisor) {
            DataPermissionAnnotationAdvisor otherAdvisor = (DataPermissionAnnotationAdvisor) other;
            return ObjectUtils.nullSafeEquals(this.getAdvice(), otherAdvisor.getAdvice()) && ObjectUtils.nullSafeEquals(this.getPointcut(), otherAdvisor.getPointcut());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return DataPermissionAnnotationAdvisor.class.hashCode();
    }
}
