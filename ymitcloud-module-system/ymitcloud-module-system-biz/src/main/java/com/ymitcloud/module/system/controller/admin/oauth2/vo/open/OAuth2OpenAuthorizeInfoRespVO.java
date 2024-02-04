package com.ymitcloud.module.system.controller.admin.oauth2.vo.open;

import java.util.List;

import com.ymitcloud.framework.common.core.KeyValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 授权页的信息 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2OpenAuthorizeInfoRespVO {

    /**
     * 客户端
     */
    private Client client;
    /**
     * scope 的选中信息,使用 List 保证有序性，Key 是 scope，Value 为是否选中
     */
    private List<KeyValue<String, Boolean>> scopes;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Client {

        /**
         * 应用名
         */
        private String name;
        /**
         * 应用图标
         */
        private String logo;

    }

}
