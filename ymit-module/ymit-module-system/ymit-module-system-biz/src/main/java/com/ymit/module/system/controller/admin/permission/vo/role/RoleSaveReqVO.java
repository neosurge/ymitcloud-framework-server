package com.ymit.module.system.controller.admin.permission.vo.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理后台 - 角色创建 Request VO
 */
@Data
@Accessors(chain = true)
public class RoleSaveReqVO {
    /**
     * 角色编号
     */
    private Long id;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String name;
    /**
     * 角色编码
     */
    @NotBlank(message = "")
    @Size(max = 100, message = "角色标志长度不能超过100个字符")
    private String code;
    /**
     * 显示顺序不能为空
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;
    /**
     * 备注
     */
    private String remark;

}
