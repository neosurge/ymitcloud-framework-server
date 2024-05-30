package com.ymit.module.system.api.errorcode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * 错误码自动生成 DTO
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class ErrorCodeAutoGenerateReqDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 应用名
     */
    @NotNull(message = "应用名不能为空")
    private String applicationName;
    /**
     * 错误码编码
     */
    @NotNull(message = "错误码编码不能为空")
    private Integer code;
    /**
     * 错误码错误提示
     */
    @NotEmpty(message = "错误码错误提示不能为空")
    private String message;

    public String getApplicationName() {
        return this.applicationName;
    }

    public ErrorCodeAutoGenerateReqDTO setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public ErrorCodeAutoGenerateReqDTO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorCodeAutoGenerateReqDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
