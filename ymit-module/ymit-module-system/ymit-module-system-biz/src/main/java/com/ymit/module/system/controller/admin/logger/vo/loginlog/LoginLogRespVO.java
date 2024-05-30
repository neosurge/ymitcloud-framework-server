package com.ymit.module.system.controller.admin.logger.vo.loginlog;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 登录日志 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class LoginLogRespVO {

    /**
     * 日志编号
     */
    @ExcelProperty("日志主键")
    private Long id;

    /**
     * 日志类型，参见 LoginLogTypeEnum 枚举类
     */
    @ExcelProperty(value = "日志类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.LOGIN_TYPE)
    private Integer logType;

    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型，参见 UserTypeEnum 枚举
     */
    private Integer userType;
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 用户账号
     */
    @ExcelProperty("用户账号")
    private String username;
    /**
     * 登录结果，参见 LoginResultEnum 枚举类
     */
    @ExcelProperty(value = "登录结果", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.LOGIN_RESULT)
    private Integer result;
    /**
     * 用户 IP
     */
    @ExcelProperty("登录 IP")
    private String userIp;
    /**
     * 浏览器 UserAgent
     */
    @ExcelProperty("浏览器 UA")
    private String userAgent;
    /**
     * 登录时间
     */
    @ExcelProperty("登录时间")
    private LocalDateTime createTime;

}
