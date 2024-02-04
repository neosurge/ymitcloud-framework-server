package com.ymitcloud.module.mp.controller.admin.menu.vo;



import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;


/** 管理后台 - 公众号菜单保存 Request VO */
@Data
public class MpMenuSaveReqVO {

    /** 公众号账号的编号 */

    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

    @NotEmpty(message = "菜单不能为空")
    @Valid
    private List<Menu> menus;


    /**
     * 管理后台 - 公众号菜单保存时的每个菜单
     */

    @Data
    public static class Menu extends MpMenuBaseVO {

        /**
         * 子菜单数组
         */
        private List<Menu> children;

    }

}
