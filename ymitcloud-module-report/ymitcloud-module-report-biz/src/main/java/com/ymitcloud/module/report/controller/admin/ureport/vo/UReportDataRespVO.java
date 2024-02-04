package com.ymitcloud.module.report.controller.admin.ureport.vo;

import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;
import com.ymitcloud.module.system.enums.DictTypeConstants;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;



import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - Ureport2 报表 */

@Data
@ExcelIgnoreUnannotated
public class UReportDataRespVO {


    /** ID*/
    @ExcelProperty("ID")
    private Long id;

    /** 文件名称*/
    @ExcelProperty("文件名称")
    private String name;

    /** 状态*/

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;


    /** 文件内容")
    @ExcelProperty("文件内容")
    private String content;

    /** 备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    /** 创建时间*/

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
