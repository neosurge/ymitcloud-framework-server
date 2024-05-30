package com.ymit.framework.web.core.filter;

import cn.hutool.core.util.StrUtil;
import com.ymit.framework.web.config.WebProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 过滤 /admin-api、/app-api 等 API 请求的过滤器
 *
 * @author Y.S
 * @date 2024.05.15
 */
public abstract class ApiRequestFilter extends OncePerRequestFilter {
    protected final WebProperties webProperties;

    public ApiRequestFilter(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 只过滤 API 请求的地址
        return !StrUtil.startWithAny(request.getRequestURI(), this.webProperties.getAdminApi().getPrefix(), this.webProperties.getAppApi().getPrefix());
    }
}
