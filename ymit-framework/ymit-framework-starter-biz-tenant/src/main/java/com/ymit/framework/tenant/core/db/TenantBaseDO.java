package com.ymit.framework.tenant.core.db;

import com.ymit.framework.mybatis.core.dataobject.BaseDO;

import java.io.Serial;
import java.util.Objects;

/**
 * 拓展多租户的 BaseDO 基类
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class TenantBaseDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 多租户编号
     */
    private Long tenantId;

    public Long getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenantBaseDO)) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }
        TenantBaseDO that = (TenantBaseDO) o;
        return Objects.equals(this.tenantId, that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.tenantId);
    }
}
