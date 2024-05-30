package com.ymit.framework.sms.core.client.dto;

import com.ymit.framework.sms.core.enums.SmsTemplateAuditStatusEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 短信模板 Response DTO
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class SmsTemplateRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 模板编号
     */
    private String id;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 审核状态
     * <p>
     * 枚举 {@link SmsTemplateAuditStatusEnum}
     */
    private Integer auditStatus;
    /**
     * 审核未通过的理由
     */
    private String auditReason;

    public String getId() {
        return this.id;
    }

    public SmsTemplateRespDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return this.content;
    }

    public SmsTemplateRespDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getAuditStatus() {
        return this.auditStatus;
    }

    public SmsTemplateRespDTO setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
        return this;
    }

    public String getAuditReason() {
        return this.auditReason;
    }

    public SmsTemplateRespDTO setAuditReason(String auditReason) {
        this.auditReason = auditReason;
        return this;
    }
}
