package com.ymit.framework.common.enums;

import cn.hutool.core.util.ArrayUtil;
import com.ymit.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 全局用户类型枚举
 *
 * @author Y.S
 * @date 2024.05.15
 */
public enum UserTypeEnum implements IntArrayValuable {
    MEMBER(1, "会员"), // 面向 c 端，普通用户
    ADMIN(2, "管理员"); // 面向 b 端，管理后台
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserTypeEnum::getValue).toArray();
    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    UserTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static UserTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getValue().equals(value), UserTypeEnum.values());
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
