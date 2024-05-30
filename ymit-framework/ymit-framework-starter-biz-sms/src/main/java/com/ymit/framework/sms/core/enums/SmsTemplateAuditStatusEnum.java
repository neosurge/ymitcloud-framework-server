package com.ymit.framework.sms.core.enums;

/**
 * 短信模板的审核状态枚举
 *
 * @author Y.S
 * @date 2024.05.23
 */
public enum SmsTemplateAuditStatusEnum {
    /**
     * 审核中
     */
    CHECKING(1),
    /**
     * 成功
     */
    SUCCESS(2),
    /**
     * 失败
     */
    FAIL(3);

    private final Integer status;

    /**
     * 短信模板审核状态枚举
     *
     * @param status 状态，1-审核中，2-成功，3-失败
     */
    SmsTemplateAuditStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }
}
