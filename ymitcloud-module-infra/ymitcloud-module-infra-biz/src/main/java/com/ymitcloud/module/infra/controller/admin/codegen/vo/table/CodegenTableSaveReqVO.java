package com.ymitcloud.module.infra.controller.admin.codegen.vo.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymitcloud.module.infra.enums.codegen.CodegenSceneEnum;
import com.ymitcloud.module.infra.enums.codegen.CodegenTemplateTypeEnum;

import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 代码生成表定义创建/修改 Response VO
 */
@Data
public class CodegenTableSaveReqVO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 生成场景，参见 CodegenSceneEnum 枚举
     */
    @NotNull(message = "导入类型不能为空")
    private Integer scene;
    /**
     * 表名称
     */
    @NotNull(message = "表名称不能为空")
    private String tableName;
    /**
     * 表描述
     */
    @NotNull(message = "表描述不能为空")
    private String tableComment;
    /**
     * 备注
     */
    private String remark;
    /**
     * 模块名
     */
    @NotNull(message = "模块名不能为空")
    private String moduleName;
    /**
     * 业务名
     */
    @NotNull(message = "业务名不能为空")
    private String businessName;
    /**
     * 类名称
     */
    @NotNull(message = "类名称不能为空")
    private String className;
    /**
     * 类描述
     */
    @NotNull(message = "类描述不能为空")
    private String classComment;
    /**
     * 作者
     */
    @NotNull(message = "作者不能为空")
    private String author;
    /**
     * 模板类型，参见 CodegenTemplateTypeEnum 枚举
     */
    @NotNull(message = "模板类型不能为空")
    private Integer templateType;
    /**
     * 前端类型，参见 CodegenFrontTypeEnum 枚举
     */
    @NotNull(message = "前端类型不能为空")
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

    @AssertTrue(message = "上级菜单不能为空，请前往 [修改生成配置 -> 生成信息] 界面，设置“上级菜单”字段")
    @JsonIgnore
    public boolean isParentMenuIdValid() {
        // 生成场景为管理后台时，必须设置上级菜单，不然生成的菜单 SQL 是无父级菜单的
        return ObjectUtil.notEqual(getScene(), CodegenSceneEnum.ADMIN.getScene()) || getParentMenuId() != null;
    }

    @AssertTrue(message = "关联的父表信息不全")
    @JsonIgnore
    public boolean isSubValid() {
        return ObjectUtil.notEqual(getTemplateType(), CodegenTemplateTypeEnum.SUB)
                || (ObjectUtil.isAllNotEmpty(masterTableId, subJoinColumnId, subJoinMany));
    }

    @AssertTrue(message = "关联的树表信息不全")
    @JsonIgnore
    public boolean isTreeValid() {
        return ObjectUtil.notEqual(templateType, CodegenTemplateTypeEnum.TREE)
                || (ObjectUtil.isAllNotEmpty(treeParentColumnId, treeNameColumnId));
    }

}
