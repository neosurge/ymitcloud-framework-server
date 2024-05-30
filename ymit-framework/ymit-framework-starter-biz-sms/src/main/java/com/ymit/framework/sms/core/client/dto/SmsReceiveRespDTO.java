package com.ymit.framework.sms.core.client.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息接收 Response DTO
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class SmsReceiveRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 是否接收成功
     */
    private Boolean success;
    /**
     * API 接收结果的编码
     */
    private String errorCode;
    /**
     * API 接收结果的说明
     */
    private String errorMsg;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户接收时间
     */
    private LocalDateTime receiveTime;
    /**
     * 短信 API 发送返回的序号
     */
    private String serialNo;
    /**
     * 短信日志编号
     * <p>
     * 对应 SysSmsLogDO 的编号
     */
    private Long logId;

    public Boolean getSuccess() {
        return this.success;
    }

    public SmsReceiveRespDTO setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public SmsReceiveRespDTO setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public SmsReceiveRespDTO setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public String getMobile() {
        return this.mobile;
    }

    public SmsReceiveRespDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public LocalDateTime getReceiveTime() {
        return this.receiveTime;
    }

    public SmsReceiveRespDTO setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public SmsReceiveRespDTO setSerialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public Long getLogId() {
        return this.logId;
    }

    public SmsReceiveRespDTO setLogId(Long logId) {
        this.logId = logId;
        return this;
    }
}
