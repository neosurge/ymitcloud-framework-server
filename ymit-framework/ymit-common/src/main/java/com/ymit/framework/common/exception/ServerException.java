package com.ymit.framework.common.exception;

import com.ymit.framework.common.exception.enums.GlobalErrorCodeConstants;

import java.io.Serial;

/**
 * 服务器异常 Exception
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class ServerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 全局错误码
     *
     * @see GlobalErrorCodeConstants
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServerException() {
    }

    public ServerException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServerException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public ServerException setMessage(String message) {
        this.message = message;
        return this;
    }
}
