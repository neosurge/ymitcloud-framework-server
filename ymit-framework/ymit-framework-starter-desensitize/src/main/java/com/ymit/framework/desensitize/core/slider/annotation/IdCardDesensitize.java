package com.ymit.framework.desensitize.core.slider.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.ymit.framework.desensitize.core.base.annotation.DesensitizeBy;
import com.ymit.framework.desensitize.core.slider.handler.IdCardDesensitization;

import java.lang.annotation.*;

/**
 * 身份证
 *
 * @author Y.S
 * @date 2024.05.23
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@DesensitizeBy(handler = IdCardDesensitization.class)
public @interface IdCardDesensitize {
    /**
     * 前缀保留长度
     */
    int prefixKeep() default 6;

    /**
     * 后缀保留长度
     */
    int suffixKeep() default 2;

    /**
     * 替换规则，身份证号码;比如：530321199204074611 脱敏之后为 530321**********11
     */
    String replacer() default "*";
}