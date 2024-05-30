package com.ymit.module.infra.controller.admin.codegen.vo.column;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 代码生成字段定义 Response VO
 */
@Data
public class CodegenColumnRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 表编号
     */
    private Long tableId;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String dataType;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     * 是否允许为空
     */
    private Boolean nullable;
    /**
     * 是否主键
     */
    private Boolean primaryKey;
    /**
     * 是否自增
     */
    private Boolean autoIncrement;
    /**
     * 排序
     */
    private Integer ordinalPosition;
    /**
     * Java 属性类型
     */
    private String javaType;
    /**
     * Java 属性名
     */
    private String javaField;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 数据示例
     */
    private String example;
    /**
     * 是否为 Create 创建操作的字段
     */
    private Boolean createOperation;
    /**
     * 是否为 Update 更新操作的字段
     */
    private Boolean updateOperation;
    /**
     * 是否为 List 查询操作的字段
     */
    private Boolean listOperation;
    /**
     * List 查询操作的条件类型，参见 CodegenColumnListConditionEnum 枚举
     */
    private String listOperationCondition;
    /**
     * 是否为 List 查询操作的返回字段
     */
    private Boolean listOperationResult;
    /**
     * 显示类型
     */
    private String htmlType;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
