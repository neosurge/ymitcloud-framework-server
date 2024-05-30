package com.ymit.framework.tenant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * 多租户配置
 *
 * @author Y.S
 * @date 2024.05.17
 */
@ConfigurationProperties(prefix = "ymit.tenant")
public class TenantProperties {
    /**
     * 租户是否开启
     */
    private static final Boolean ENABLE_DEFAULT = true;
    /**
     * 是否开启
     */
    private Boolean enable = ENABLE_DEFAULT;
    /**
     * 需要忽略多租户的请求
     * <p>
     * 默认情况下，每个请求需要带上 tenant-id 的请求头。但是，部分请求是无需带上的，例如说短信回调、支付回调等 Open API！
     */
    private Set<String> ignoreUrls = Collections.emptySet();
    /**
     * 需要忽略多租户的表
     * <p>
     * 即默认所有表都开启多租户的功能，所以记得添加对应的 tenant_id 字段哟
     */
    private Set<String> ignoreTables = Collections.emptySet();

    public TenantProperties(Boolean enable, Set<String> ignoreUrls, Set<String> ignoreTables) {
        this.enable = enable;
        this.ignoreUrls = ignoreUrls;
        this.ignoreTables = ignoreTables;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Set<String> getIgnoreUrls() {
        return this.ignoreUrls;
    }

    public void setIgnoreUrls(Set<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    public Set<String> getIgnoreTables() {
        return this.ignoreTables;
    }

    public void setIgnoreTables(Set<String> ignoreTables) {
        this.ignoreTables = ignoreTables;
    }
}
