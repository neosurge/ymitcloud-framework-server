package com.ymitcloud.module.infra.controller.admin.codegen.vo.table;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 代码生成表定义 Response VO
 */
@Data
public class CodegenTableRespVO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 生成场景，参见 CodegenSceneEnum 枚举
     */
    private Integer scene;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;
    /**
     * 备注
     */
    private String remark;
    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 业务名
     */
    private String businessName;
    /**
     * 类名称
     */
    private String className;
    /**
     * 类描述
     */
    private String classComment;

    /**
     * 作者
     */
    private String author;

    /**
     * 模板类型，参见 CodegenTemplateTypeEnum 枚举
     */
    private Integer templateType;
    /**
     * 前端类型，参见 CodegenFrontTypeEnum 枚举
     */
    private Integer frontType;

    /**
     * 父菜单编号
     */
    private Long parentMenuId;

    /**
     * 主表的编号
     */
    private Long masterTableId;
    /**
     * 子表关联主表的字段编号
     */
    private Long subJoinColumnId;
    /**
     * 主表与子表是否一对多
     */
    private Boolean subJoinMany;

    /**
     * 树表的父字段编号
     */
    private Long treeParentColumnId;
    /**
     * 树表的名字字段编号
     */
    private Long treeNameColumnId;
    /**
     * 主键编号
     */
    private Integer dataSourceConfigId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
