package com.ymit.module.system.api.logger.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志创建 Request DTO
 *
 * @author Y.S
 * @date 2024.05.22
 */
public class OperateLogCreateReqDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    private Integer userType;
    /**
     * 操作模块
     */
    @NotEmpty(message = "操作模块不能为空")
    private String module;
    /**
     * 操作名
     */
    @NotEmpty(message = "操作名")
    private String name;
    /**
     * 操作分类
     */
    @NotNull(message = "操作分类不能为空")
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
    @NotEmpty(message = "请求地址不能为空")
    private String requestUrl;
    /**
     * 用户 IP
     */
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;
    /**
     * 浏览器 UserAgent
     */
    @NotEmpty(message = "浏览器 UserAgent 不能为空")
    private String userAgent;
    /**
     * Java 方法名
     */
    @NotEmpty(message = "Java 方法名不能为空")
    private String javaMethod;
    /**
     * Java 方法的参数
     */
    private String javaMethodArgs;
    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;
    /**
     * 执行时长，单位：毫秒
     */
    @NotNull(message = "执行时长不能为空")
    private Integer duration;
    /**
     * 结果码
     */
    @NotNull(message = "结果码不能为空")
    private Integer resultCode;
    /**
     * 结果提示
     */
    private String resultMsg;
    /**
     * 结果数据
     */
    private String resultData;

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getExts() {
        return this.exts;
    }

    public void setExts(Map<String, Object> exts) {
        this.exts = exts;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getUserIp() {
        return this.userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getJavaMethod() {
        return this.javaMethod;
    }

    public void setJavaMethod(String javaMethod) {
        this.javaMethod = javaMethod;
    }

    public String getJavaMethodArgs() {
        return this.javaMethodArgs;
    }

    public void setJavaMethodArgs(String javaMethodArgs) {
        this.javaMethodArgs = javaMethodArgs;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultData() {
        return this.resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}
