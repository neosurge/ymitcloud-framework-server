package com.ymit.module.system.controller.admin.errorcode.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 错误码 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class ErrorCodeRespVO {
    /**
     * 错误码编号
     */
    @ExcelProperty("错误码编号")
    private Long id;
    /**
     * 错误码类型，参见 ErrorCodeTypeEnum 枚举类
     */
    @ExcelProperty(value = "错误码类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.ERROR_CODE_TYPE)
    private Integer type;
    /**
     * 应用名
     */
    @ExcelProperty("应用名")
    private String applicationName;

    /**
     * 错误码编码
     */
    @ExcelProperty("错误码编码")
    private Integer code;
    /**
     * 错误码错误提示
     */
    @ExcelProperty("错误码错误提示")
    private String message;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String memo;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
