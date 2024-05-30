package com.ymit.module.system.api.permission.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门的数据权限 Response DTO
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class DeptDataPermissionRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 是否可查看全部数据
     */
    private Boolean all;
    /**
     * 是否可查看自己的数据
     */
    private Boolean self;
    /**
     * 可查看的部门编号数组
     */
    private Set<Long> deptIds;

    public DeptDataPermissionRespDTO() {
        this.all = false;
        this.self = false;
        this.deptIds = new HashSet<>();
    }

    public Boolean getAll() {
        return this.all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public Boolean getSelf() {
        return this.self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    public Set<Long> getDeptIds() {
        return this.deptIds;
    }

    public void setDeptIds(Set<Long> deptIds) {
        this.deptIds = deptIds;
    }
}
