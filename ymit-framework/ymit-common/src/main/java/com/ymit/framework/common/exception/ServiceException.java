package com.ymit.framework.common.exception;

import com.ymit.framework.common.exception.enums.ServiceErrorCodeRange;

import java.io.Serial;

/**
 * 业务逻辑异常 Exception
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 业务错误码
     *
     * @see ServiceErrorCodeRange
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }
}
