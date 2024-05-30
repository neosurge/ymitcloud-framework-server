package com.ymit.framework.web.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

/**
 * 系统入口路径配置类
 *
 * @author Y.S
 * @date 2024.05.15
 */
@ConfigurationProperties(prefix = "ymit.web")
@Validated
public class WebProperties {

    @NotNull(message = "APP API 不能为空")
    private final Api appApi = new Api("/app-api", "**.controller.app.**");
    @NotNull(message = "Admin API 不能为空")
    private final Api adminApi = new Api("/admin-api", "**.controller.admin.**");
    @NotNull(message = "Admin UI 不能为空")
    private Ui adminUi;

    public Api getAppApi() {
        return this.appApi;
    }

    public Api getAdminApi() {
        return this.adminApi;
    }

    public Ui getAdminUi() {
        return this.adminUi;
    }

    public void setAdminUi(Ui adminUi) {
        this.adminUi = adminUi;
    }

    @Valid
    public static class Api {

        /**
         * API 前缀，实现所有 Controller 提供的 RESTFul API 的统一前缀
         * <p>
         * 意义：通过该前缀，避免 Swagger、Actuator 意外通过 Nginx 暴露出来给外部，带来安全性问题
         * 这样，Nginx 只需要配置转发到 /api/* 的所有接口即可。
         *
         * @see YmitWebAutoConfiguration#configurePathMatch(PathMatchConfigurer)
         */
        @NotEmpty(message = "API 前缀不能为空")
        private String prefix;

        /**
         * Controller 所在包的 Ant 路径规则
         * <p>
         * 主要目的是，给该 Controller 设置指定的 {@link #prefix}
         */
        @NotEmpty(message = "Controller 所在包不能为空")
        private String controller;

        public Api() {
        }

        public Api(String prefix, String controller) {
            this.prefix = prefix;
            this.controller = controller;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getController() {
            return this.controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }
    }

    @Valid
    public static class Ui {

        /**
         * 访问地址
         */
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
