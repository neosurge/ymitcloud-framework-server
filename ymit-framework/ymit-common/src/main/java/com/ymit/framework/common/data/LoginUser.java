package com.ymit.framework.common.data;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymit.framework.common.enums.UserTypeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录用户信息
 *
 * @author Y.S
 * @date 2024.05.16
 */
public class LoginUser {
    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     * <p>
     * 关联 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 租户编号
     */
    private Long tenantId;
    /**
     * 授权范围
     */
    private List<String> scopes;
    // ========== 上下文 ==========
    /**
     * 上下文字段，不进行持久化
     * <p>
     * 1. 用于基于 LoginUser 维度的临时缓存
     */
    @JsonIgnore
    private Map<String, Object> context;

    public void setContext(String key, Object value) {
        if (this.context == null) {
            this.context = new HashMap<>();
        }
        this.context.put(key, value);
    }

    public <T> T getContext(String key, Class<T> type) {
        return MapUtil.get(this.context, key, type);
    }

    public Long getId() {
        return this.id;
    }

    public LoginUser setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public LoginUser setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public LoginUser setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public List<String> getScopes() {
        return this.scopes;
    }

    public LoginUser setScopes(List<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public Map<String, Object> getContext() {
        return this.context;
    }

    public LoginUser setContext(Map<String, Object> context) {
        this.context = context;
        return this;
    }
}
