package com.ymit.framework.sms.core.client.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * 短信发送 Response DTO
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class SmsSendRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * API 请求编号
     */
    private String apiRequestId;
    // ==================== 成功时字段 ====================
    /**
     * 短信 API 发送返回的序号
     */
    private String serialNo;
    // ==================== 失败时字段 ====================
    /**
     * API 返回错误码
     * <p>
     * 由于第三方的错误码可能是字符串，所以使用 String 类型
     */
    private String apiCode;
    /**
     * API 返回提示
     */
    private String apiMsg;

    public Boolean getSuccess() {
        return this.success;
    }

    public SmsSendRespDTO setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getApiRequestId() {
        return this.apiRequestId;
    }

    public SmsSendRespDTO setApiRequestId(String apiRequestId) {
        this.apiRequestId = apiRequestId;
        return this;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public SmsSendRespDTO setSerialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public String getApiCode() {
        return this.apiCode;
    }

    public SmsSendRespDTO setApiCode(String apiCode) {
        this.apiCode = apiCode;
        return this;
    }

    public String getApiMsg() {
        return this.apiMsg;
    }

    public SmsSendRespDTO setApiMsg(String apiMsg) {
        this.apiMsg = apiMsg;
        return this;
    }
}
