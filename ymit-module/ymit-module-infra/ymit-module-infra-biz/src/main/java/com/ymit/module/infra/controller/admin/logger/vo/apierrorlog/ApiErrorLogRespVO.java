package com.ymit.module.infra.controller.admin.logger.vo.apierrorlog;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.infra.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - API 错误日志 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class ApiErrorLogRespVO {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Integer id;
    /**
     * 链路追踪编号
     */
    @ExcelProperty("链路追踪编号")
    private String traceId;
    /**
     * 用户编号
     */
    @ExcelProperty("用户编号")
    private Integer userId;
    /**
     * 用户类型
     */
    @ExcelProperty(value = "用户类型", converter = DictConvert.class)
    @DictFormat(com.ymit.module.system.enums.DictTypeConstants.USER_TYPE)
    private Integer userType;
    /**
     * 应用名
     */
    @ExcelProperty("应用名")
    private String applicationName;
    /**
     * 请求方法名
     */
    @ExcelProperty("请求方法名")
    private String requestMethod;
    /**
     * 请求地址
     */
    @ExcelProperty("请求地址")
    private String requestUrl;
    /**
     * 请求参数
     */
    @ExcelProperty("请求参数")
    private String requestParams;
    /**
     * 用户 IP
     */
    @ExcelProperty("用户 IP")
    private String userIp;
    /**
     * 浏览器 UA
     */
    @ExcelProperty("浏览器 UA")
    private String userAgent;
    /**
     * 异常发生时间
     */
    @ExcelProperty("异常发生时间")
    private LocalDateTime exceptionTime;
    /**
     * 异常名
     */
    @ExcelProperty("异常名")
    private String exceptionName;
    /**
     * 异常导致的消息
     */
    @ExcelProperty("异常导致的消息")
    private String exceptionMessage;
    /**
     * 异常导致的根消息
     */
    @ExcelProperty("异常导致的根消息")
    private String exceptionRootCauseMessage;
    /**
     * 异常的栈轨迹
     */
    @ExcelProperty("异常的栈轨迹")
    private String exceptionStackTrace;
    /**
     * 异常发生的类全名
     */
    @ExcelProperty("异常发生的类全名")
    private String exceptionClassName;
    /**
     * 异常发生的类文件
     */
    @ExcelProperty("异常发生的类文件")
    private String exceptionFileName;
    /**
     * 异常发生的方法名
     */
    @ExcelProperty("异常发生的方法名")
    private String exceptionMethodName;
    /**
     * 异常发生的方法所在行
     */
    @ExcelProperty("异常发生的方法所在行")
    private Integer exceptionLineNumber;
    /**
     * 处理状态
     */
    @ExcelProperty(value = "处理状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.API_ERROR_LOG_PROCESS_STATUS)
    private Integer processStatus;
    /**
     * 处理时间
     */
    @ExcelProperty("处理时间")
    private LocalDateTime processTime;
    /**
     * 处理用户编号
     */
    @ExcelProperty("处理用户编号")
    private Integer processUserId;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
