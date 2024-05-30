package com.ymit.module.infra.controller.admin.db.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 数据源配置创建/修改 Request VO
 */
@Data
public class DataSourceConfigSaveReqVO {

    /**
     * 主键编号
     */
    private Long id;
    /**
     * 数据源名称
     */
    @NotNull(message = "数据源名称不能为空")
    private String name;
    /**
     * 数据源连接
     */
    @NotNull(message = "数据源连接不能为空")
    private String url;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

}
