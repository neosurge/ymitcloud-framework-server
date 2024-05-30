package com.ymit.framework.excel.core.annotations;

import java.lang.annotation.*;

/**
 * 登录日志 Service 接口
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DictFormat {
    /**
     * 例如说，SysDictTypeConstants、InfDictTypeConstants
     *
     * @return 字典类型
     */
    String value();
}
