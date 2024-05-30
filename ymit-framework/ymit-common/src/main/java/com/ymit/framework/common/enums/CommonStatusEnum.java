package com.ymit.framework.common.enums;

import cn.hutool.core.util.ObjUtil;
import com.ymit.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 通用状态枚举
 *
 * @author Y.S
 * @date 2024.05.15
 */
public enum CommonStatusEnum implements IntArrayValuable {
    ENABLE(0, "开启"),
    DISABLE(1, "关闭");
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CommonStatusEnum::getStatus).toArray();
    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    CommonStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static boolean isEnable(Integer status) {
        return ObjUtil.equal(ENABLE.status, status);
    }

    public static boolean isDisable(Integer status) {
        return ObjUtil.equal(DISABLE.status, status);
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}
