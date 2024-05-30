package com.ymit.module.system.controller.admin.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * 管理后台 - 登录用户的权限信息 Response VO，额外包括用户信息和角色列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthPermissionInfoRespVO {
    /**
     * 用户信息
     */
    private UserVO user;
    /**
     * 角色标识数组
     */
    private Set<String> roles;
    /**
     * 操作权限数组
     */
    private Set<String> permissions;
    /**
     * 菜单树
     */
    private List<MenuVO> menus;

    /**
     * 用户信息 VO
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserVO {
        /**
         * 用户编号
         */
        private Long id;
        /**
         * 用户昵称
         */
        private String nickname;
        /**
         * 用户头像
         */
        private String avatar;

    }

    /**
     * 管理后台 - 登录用户的菜单信息 Response VO
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuVO {
        /**
         * 菜单名称
         */
        private Long id;
        /**
         * 父菜单 ID
         */
        private Long parentId;
        /**
         * 菜单名称
         */
        private String name;
        /**
         * 路由地址,仅菜单类型为菜单或者目录时，才需要传
         */
        private String path;
        /**
         * 组件路径,仅菜单类型为菜单时，才需要传
         */
        private String component;
        /**
         * 组件名
         */
        private String componentName;
        /**
         * 菜单图标,仅菜单类型为菜单或者目录时，才需要传
         */
        private String icon;
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

        /**
         * 子路由
         */
        private List<MenuVO> children;

    }

}
