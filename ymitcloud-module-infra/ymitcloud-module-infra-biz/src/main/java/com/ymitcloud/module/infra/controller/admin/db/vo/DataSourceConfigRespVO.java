package com.ymitcloud.module.infra.controller.admin.db.vo;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 数据源配置 Response VO
 */
@Data
public class DataSourceConfigRespVO {
    /**
     * 主键编号
     */
    private Integer id;
    /**
     * 数据源名称
     */
    private String name;
    /**
     * 数据源连接
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
