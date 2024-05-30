package com.ymit.module.system.controller.admin.logger.vo.operatelog;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 管理后台 - 操作日志 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class OperateLogRespVO {

    /**
     * 日志编号
     */
    @ExcelProperty("日志编号")
    private Long id;

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户昵称
     */
    @ExcelProperty("操作人")
    private String userNickname;

    /**
     * 操作模块
     */
    @ExcelProperty("操作模块")
    private String module;

    /**
     * 操作名
     */
    @ExcelProperty("操作名")
    private String name;

    /**
     * 操作分类，参见 OperateLogTypeEnum 枚举类
     */
    @ExcelProperty(value = "操作类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.OPERATE_TYPE)
    private Integer type;

    /**
     * 操作明细
     */
    private String content;

    /**
     * 拓展字段
     */
    private Map<String, Object> exts;
    /**
     * 请求方法名
     */
    @NotEmpty(message = "请求方法名不能为空")
    private String requestMethod;
    /**
     * 请求地址
     */
    private String requestUrl;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 浏览器 UserAgent
     */
    private String userAgent;
    /**
     * Java 方法名
     */
    private String javaMethod;
    /**
     * Java 方法的参数
     */
    private String javaMethodArgs;
    /**
     * 开始时间
     */
    @ExcelProperty("操作日志")
    private LocalDateTime startTime;
    /**
     * 执行时长，单位：毫秒
     */
    @ExcelProperty("执行时长")
    private Integer duration;
    /**
     * 结果码
     */
    @ExcelProperty(value = "结果码")
    private Integer resultCode;
    /**
     * 结果提示
     */
    private String resultMsg;
    /**
     * 结果数据
     */
    private String resultData;

}
