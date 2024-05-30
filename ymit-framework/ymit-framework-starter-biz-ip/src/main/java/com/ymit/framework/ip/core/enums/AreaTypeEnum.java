package com.ymit.framework.ip.core.enums;

import com.ymit.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 区域类型枚举
 *
 * @author Y.S
 * @date 2024.05.23
 */
public enum AreaTypeEnum implements IntArrayValuable {
    /**
     * 国家
     */
    COUNTRY(1, "国家"),
    /**
     * 省份
     */
    PROVINCE(2, "省份"),
    /**
     * 城市
     */
    CITY(3, "城市"),
    /**
     * 地区(县、镇、区等)
     */
    DISTRICT(4, "地区"),
    ;
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AreaTypeEnum::getType).toArray();
    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名字
     */
    private final String name;

    AreaTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
