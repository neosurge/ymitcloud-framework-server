package com.ymit.module.infra.controller.admin.codegen.vo.table;

import lombok.Data;

/**
 * 管理后台 - 数据库的表定义 Response VO
 */
@Data
public class DatabaseTableRespVO {
    /**
     * 表名称
     */
    private String name;
    /**
     * 表描述
     */
    private String comment;

}
