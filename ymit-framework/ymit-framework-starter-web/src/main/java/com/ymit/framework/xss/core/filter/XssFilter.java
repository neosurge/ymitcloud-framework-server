package com.ymit.framework.xss.core.filter;

import com.ymit.framework.xss.config.XssProperties;
import com.ymit.framework.xss.core.clean.XssCleaner;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Xss 过滤器
 *
 * @author Y.S
 * @date 2024.05.20
 */
public class XssFilter extends OncePerRequestFilter {
    /**
     * 属性
     */
    private final XssProperties properties;
    /**
     * 路径匹配器
     */
    private final PathMatcher pathMatcher;
    private final XssCleaner xssCleaner;

    public XssFilter(XssProperties properties, PathMatcher pathMatcher, XssCleaner xssCleaner) {
        this.properties = properties;
        this.pathMatcher = pathMatcher;
        this.xssCleaner = xssCleaner;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new XssRequestWrapper(request, this.xssCleaner), response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        // 如果关闭，则不过滤
        if (!this.properties.isEnable()) {
            return true;
        }
        // 如果匹配到无需过滤，则不过滤
        String uri = request.getRequestURI();
        return this.properties.getExcludeUrls().stream().anyMatch(excludeUrl -> this.pathMatcher.match(excludeUrl, uri));
    }
}
