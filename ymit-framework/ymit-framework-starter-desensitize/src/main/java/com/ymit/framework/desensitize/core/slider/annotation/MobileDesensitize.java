package com.ymit.framework.desensitize.core.slider.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.ymit.framework.desensitize.core.base.annotation.DesensitizeBy;
import com.ymit.framework.desensitize.core.slider.handler.MobileDesensitization;

import java.lang.annotation.*;

/**
 * 手机号
 *
 * @author Y.S
 * @date 2024.05.23
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@DesensitizeBy(handler = MobileDesensitization.class)
public @interface MobileDesensitize {
    /**
     * 前缀保留长度
     */
    int prefixKeep() default 3;

    /**
     * 后缀保留长度
     */
    int suffixKeep() default 4;

    /**
     * 替换规则，手机号;比如：13248765917 脱敏之后为 132****5917
     */
    String replacer() default "*";
}
