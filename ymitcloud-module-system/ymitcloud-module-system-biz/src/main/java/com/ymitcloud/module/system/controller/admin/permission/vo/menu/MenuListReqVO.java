package com.ymitcloud.module.system.controller.admin.permission.vo.menu;

import lombok.Data;

/**
 * 管理后台 - 菜单列表 Request VO
 */
@Data
public class MenuListReqVO {

    /**
     * 菜单名称，模糊匹配
     */
    private String name;

    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;

}
