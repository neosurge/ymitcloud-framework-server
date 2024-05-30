package com.ymit.module.infra.controller.admin.codegen.vo;

import com.ymit.module.infra.controller.admin.codegen.vo.column.CodegenColumnSaveReqVO;
import com.ymit.module.infra.controller.admin.codegen.vo.table.CodegenTableSaveReqVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 代码生成表和字段的修改 Request VO
 */
@Data
public class CodegenUpdateReqVO {

    @Valid // 校验内嵌的字段
    @NotNull(message = "表定义不能为空")
    private CodegenTableSaveReqVO table;

    @Valid // 校验内嵌的字段
    @NotNull(message = "字段定义不能为空")
    private List<CodegenColumnSaveReqVO> columns;

}
