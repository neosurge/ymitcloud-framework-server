package com.ymit.module.system.controller.admin.permission.vo.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 管理后台 - 菜单创建/修改 Request VO
 */
@Data
public class MenuSaveVO {
    /**
     * 菜单编号
     */
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;
    /**
     * 权限标识,仅菜单类型为按钮时，才需要传递
     */
    @Size(max = 100)
    private String permission;
    /**
     * 类型，参见 MenuTypeEnum 枚举类
     */
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    /**
     * 显示顺序不能为空
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    /**
     * 父菜单 ID
     */
    @NotNull(message = "父菜单 ID 不能为空")
    private Long parentId;

    /**
     * 路由地址,仅菜单类型为菜单或者目录时，才需要传
     */
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /**
     * 菜单图标,仅菜单类型为菜单或者目录时，才需要传
     */
    private String icon;
    /**
     * 组件路径,仅菜单类型为菜单时，才需要传
     */
    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;
    /**
     * 组件名
     */
    private String componentName;
    /**
     * 状态,见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
     * 是否缓存
     */
    private Boolean keepAlive;
    /**
     * 是否总是显示
     */
    private Boolean alwaysShow;

}
