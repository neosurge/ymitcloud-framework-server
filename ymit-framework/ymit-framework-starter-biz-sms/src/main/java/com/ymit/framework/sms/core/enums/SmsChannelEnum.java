package com.ymit.framework.sms.core.enums;

import cn.hutool.core.util.ArrayUtil;

/**
 * 短信渠道枚举
 *
 * @author Y.S
 * @date 2024.05.23
 */
public enum SmsChannelEnum {
    DEBUG_DING_TALK("DEBUG_DING_TALK", "调试(钉钉)"),
    ALIYUN("ALIYUN", "阿里云"),
    TENCENT("TENCENT", "腾讯云"),
//    HUA_WEI("HUA_WEI", "华为云"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名字
     */
    private final String name;

    SmsChannelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SmsChannelEnum getByCode(String code) {
        return ArrayUtil.firstMatch(o -> o.getCode().equals(code), values());
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
