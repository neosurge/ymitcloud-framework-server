package com.ymitcloud.module.infra.controller.admin.codegen.vo.column;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 代码生成字段定义创建/修改 Request VO
 */
@Data
public class CodegenColumnSaveReqVO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 表编号
     */
    @NotNull(message = "表编号不能为空")
    private Long tableId;

    /**
     * 字段名
     */
    @NotNull(message = "字段名不能为空")
    private String columnName;

    /**
     * 字段类型
     */
    @NotNull(message = "字段类型不能为空")
    private String dataType;

    /**
     * 字段描述
     */
    @NotNull(message = "字段描述不能为空")
    private String columnComment;

    /**
     * 是否允许为空
     */
    @NotNull(message = "是否允许为空不能为空")
    private Boolean nullable;
    /**
     * 是否主键
     */
    @NotNull(message = "是否主键不能为空")
    private Boolean primaryKey;
    /**
     * 是否自增
     */
    @NotNull(message = "是否自增不能为空")
    private Boolean autoIncrement;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer ordinalPosition;
    /**
     * Java 属性类型
     */
    @NotNull(message = "Java 属性类型不能为空")
    private String javaType;
    /**
     * Java 属性名
     */
    @NotNull(message = "Java 属性名不能为空")
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
    @NotNull(message = "是否为 Create 创建操作的字段不能为空")
    private Boolean createOperation;
    /**
     * 是否为 Update 更新操作的字段
     */
    @NotNull(message = "是否为 Update 更新操作的字段不能为空")
    private Boolean updateOperation;
    /**
     * 是否为 List 查询操作的字段
     */
    @NotNull(message = "是否为 List 查询操作的字段不能为空")
    private Boolean listOperation;
    /**
     * List 查询操作的条件类型，参见 CodegenColumnListConditionEnum 枚举
     */
    @NotNull(message = "List 查询操作的条件类型不能为空")
    private String listOperationCondition;
    /**
     * 是否为 List 查询操作的返回字段
     */
    @NotNull(message = "是否为 List 查询操作的返回字段不能为空")
    private Boolean listOperationResult;
    /**
     * 显示类型
     */
    @NotNull(message = "显示类型不能为空")
    private String htmlType;

}
