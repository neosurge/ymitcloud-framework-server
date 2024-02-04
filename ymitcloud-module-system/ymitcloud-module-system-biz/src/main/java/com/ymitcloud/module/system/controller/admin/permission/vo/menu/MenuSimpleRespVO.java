package com.ymitcloud.module.system.controller.admin.permission.vo.menu;

import lombok.Data;

/**
 * 管理后台 - 菜单精简信息 Response VO
 */
@Data
public class MenuSimpleRespVO {

    /**
     * 菜单编号
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单 ID
     */
    private Long parentId;
    /**
     * 类型，参见 MenuTypeEnum 枚举类
     */
    private Integer type;

}
