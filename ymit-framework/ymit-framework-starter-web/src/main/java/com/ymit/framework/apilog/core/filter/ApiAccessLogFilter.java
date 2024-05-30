package com.ymit.framework.apilog.core.filter;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import com.ymit.framework.apilog.core.service.ApiAccessLog;
import com.ymit.framework.apilog.core.service.ApiAccessLogFrameworkService;
import com.ymit.framework.common.data.CommonResult;
import com.ymit.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.ymit.framework.common.util.json.JsonUtils;
import com.ymit.framework.common.util.monitor.TracerUtils;
import com.ymit.framework.common.util.servlet.ServletUtils;
import com.ymit.framework.web.config.WebProperties;
import com.ymit.framework.web.core.filter.ApiRequestFilter;
import com.ymit.framework.web.core.util.WebFrameworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * API 访问日志 Filter
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class ApiAccessLogFilter extends ApiRequestFilter {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ApiAccessLogFilter.class);
    private final String applicationName;
    private final ApiAccessLogFrameworkService apiAccessLogFrameworkService;

    /**
     * 构造函数
     *
     * @param webProperties                web配置
     * @param applicationName              应用名
     * @param apiAccessLogFrameworkService API 访问日志的调用接口
     */
    public ApiAccessLogFilter(WebProperties webProperties, String applicationName, ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        super(webProperties);
        this.applicationName = applicationName;
        this.apiAccessLogFrameworkService = apiAccessLogFrameworkService;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获得开始时间
        LocalDateTime beginTime = LocalDateTime.now();
        // 提前获得参数，避免 XssFilter 过滤处理
        Map<String, String> queryString = ServletUtils.getParamMap(request);
        String requestBody = ServletUtils.isJsonRequest(request) ? ServletUtils.getBody(request) : null;
        try {
            // 继续过滤器
            filterChain.doFilter(request, response);
            // 正常执行，记录日志
            this.createApiAccessLog(request, beginTime, queryString, requestBody, null);
        } catch (Exception ex) {
            // 异常执行，记录日志
            this.createApiAccessLog(request, beginTime, queryString, requestBody, ex);
            throw ex;
        }
    }

    private void createApiAccessLog(HttpServletRequest request, LocalDateTime beginTime, Map<String, String> queryString, String requestBody, Exception ex) {
        ApiAccessLog accessLog = new ApiAccessLog();
        try {
            this.buildApiAccessLogDTO(accessLog, request, beginTime, queryString, requestBody, ex);
            this.apiAccessLogFrameworkService.createApiAccessLog(accessLog);
        } catch (Throwable th) {
            log.error("[createApiAccessLog][url({}) log({}) 发生异常]", request.getRequestURI(), JsonUtils.toJsonString(accessLog), th);
        }
    }

    private void buildApiAccessLogDTO(ApiAccessLog accessLog, HttpServletRequest request, LocalDateTime beginTime, Map<String, String> queryString, String requestBody, Exception ex) {
        // 处理用户信息
        accessLog.setUserId(WebFrameworkUtils.getLoginUserId(request));
        accessLog.setUserType(WebFrameworkUtils.getLoginUserType(request));
        // 设置访问结果
        CommonResult<?> result = WebFrameworkUtils.getCommonResult(request);
        if (result != null) {
            accessLog.setResultCode(result.getCode());
            accessLog.setResultMsg(result.getMsg());
        } else if (ex != null) {
            accessLog.setResultCode(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode());
            accessLog.setResultMsg(ExceptionUtil.getRootCauseMessage(ex));
        } else {
            accessLog.setResultCode(0);
            accessLog.setResultMsg("");
        }
        // 设置其它字段
        accessLog.setTraceId(TracerUtils.getTraceId());
        accessLog.setApplicationName(this.applicationName);
        accessLog.setRequestUrl(request.getRequestURI());
        Map<String, Object> requestParams = MapUtil.<String, Object>builder().put("query", queryString).put("body", requestBody).build();
        accessLog.setRequestParams(JsonUtils.toJsonString(requestParams));
        accessLog.setRequestMethod(request.getMethod());
        accessLog.setUserAgent(ServletUtils.getUserAgent(request));
        accessLog.setUserIp(ServletUtils.getClientIP(request));
        // 持续时间
        accessLog.setBeginTime(beginTime);
        accessLog.setEndTime(LocalDateTime.now());
        accessLog.setDuration((int) LocalDateTimeUtil.between(accessLog.getBeginTime(), accessLog.getEndTime(), ChronoUnit.MILLIS));
    }
}
