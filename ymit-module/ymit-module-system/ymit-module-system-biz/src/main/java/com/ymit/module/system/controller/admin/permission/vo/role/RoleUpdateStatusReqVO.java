package com.ymit.module.system.controller.admin.permission.vo.role;

import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.validation.InEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 角色更新状态 Request VO
 */
@Data
public class RoleUpdateStatusReqVO {
    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Long id;
    /**
     * 状态,见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
