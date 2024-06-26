package com.ymit.module.system.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别的枚举值
 *
 * @author 云码源码
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    /**
     * 男
     */
    MALE(1),
    /**
     * 女
     */
    FEMALE(2),
    /* 未知 */
    UNKNOWN(3);

    /**
     * 性别
     */
    private final Integer gender;

}
