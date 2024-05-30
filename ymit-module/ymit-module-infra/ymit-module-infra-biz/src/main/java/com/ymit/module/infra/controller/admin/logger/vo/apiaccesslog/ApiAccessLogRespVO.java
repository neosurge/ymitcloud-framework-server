package com.ymit.module.infra.controller.admin.logger.vo.apiaccesslog;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - API 访问日志 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class ApiAccessLogRespVO {
    /**
     * 日志主键
     */
    @ExcelProperty("日志主键")
    private Long id;
    /**
     * 链路追踪编号
     */
    @ExcelProperty("链路追踪编号")
    private String traceId;
    /**
     * 用户编号
     */
    @ExcelProperty("用户编号")
    private Long userId;
    /**
     * 用户类型，参见 UserTypeEnum 枚举
     */
    @ExcelProperty(value = "用户类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.USER_TYPE)
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
     * 开始请求时间
     */
    @ExcelProperty("开始请求时间")
    private LocalDateTime beginTime;
    /**
     * 结束请求时间
     */
    @ExcelProperty("结束请求时间")
    private LocalDateTime endTime;
    /**
     * 执行时长
     */
    @ExcelProperty("执行时长")
    private Integer duration;
    /**
     * 结果码
     */
    @ExcelProperty("结果码")
    private Integer resultCode;
    /**
     * 结果提示
     */
    @ExcelProperty("结果提示")
    private String resultMsg;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
