package com.ymitcloud.module.infra.controller.admin.config.vo;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;
import com.ymitcloud.module.infra.enums.DictTypeConstants;

import lombok.Data;

/**
 * 管理后台 - 参数配置信息 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class ConfigRespVO {
    /**
     * 参数配置序号
     */
    @ExcelProperty("参数配置序号")
    private Long id;
    /**
     * 参数分类
     */
    @ExcelProperty("参数分类")
    private String category;
    /**
     * 参数名称
     */
    @ExcelProperty("参数名称")
    private String name;
    /**
     * 参数键名
     */
    @ExcelProperty("参数键名")
    private String key;
    /**
     * 参数键值
     */
    @ExcelProperty("参数键值")
    private String value;

    /**
     * 参数类型，参见 SysConfigTypeEnum 枚举
     */
    @ExcelProperty(value = "参数类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.CONFIG_TYPE)
    private Integer type;

    /**
     * 是否可见
     */
    @ExcelProperty(value = "是否可见", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.BOOLEAN_STRING)
    private Boolean visible;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
