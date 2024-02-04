package com.ymitcloud.module.infra.controller.admin.codegen.vo;

import java.util.List;

import com.ymitcloud.module.infra.controller.admin.codegen.vo.column.CodegenColumnRespVO;
import com.ymitcloud.module.infra.controller.admin.codegen.vo.table.CodegenTableRespVO;

import lombok.Data;

/**
 * 管理后台 - 代码生成表和字段的明细 Response VO
 */
@Data
public class CodegenDetailRespVO {

    /**
     * 表定义
     */
    private CodegenTableRespVO table;

    /**
     * 字段定义
     */
    private List<CodegenColumnRespVO> columns;

}
