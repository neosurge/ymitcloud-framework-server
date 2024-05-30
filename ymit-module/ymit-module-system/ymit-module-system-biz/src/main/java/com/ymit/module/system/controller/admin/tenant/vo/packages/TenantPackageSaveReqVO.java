package com.ymit.module.system.controller.admin.tenant.vo.packages;

import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.validation.InEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

/**
 * 管理后台 - 租户套餐创建/修改 Request VO
 */
@Data
public class TenantPackageSaveReqVO {
    /**
     * 套餐编号
     */
    private Long id;
    /**
     * 套餐名
     */
    @NotEmpty(message = "套餐名不能为空")
    private String name;
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联的菜单编号
     */
    @NotNull(message = "关联的菜单编号不能为空")
    private Set<Long> menuIds;

}
