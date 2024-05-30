package com.ymit.module.infra.controller.admin.codegen.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理后台 - 代码生成预览 Response VO，注意，每个文件都是一个该对象
 */
@Data
@Accessors(chain = true)
public class CodegenPreviewRespVO {

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 代码
     */
    private String code;

}
