package com.ymit.module.system.controller.admin.sensitiveword.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.framework.excel.core.convert.JsonConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理后台 - 敏感词 Response VO
 */
@Data
public class SensitiveWordRespVO {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Long id;
    /**
     * 敏感词
     */
    @ExcelProperty("敏感词")
    private String name;
    /**
     * 标签
     */
    @ExcelProperty(value = "标签", converter = JsonConvert.class)
    private List<String> tags;
    /**
     * 状态，参见 CommonStatusEnum 枚举类
     */
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 描述
     */
    @ExcelProperty("描述")
    private String description;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
