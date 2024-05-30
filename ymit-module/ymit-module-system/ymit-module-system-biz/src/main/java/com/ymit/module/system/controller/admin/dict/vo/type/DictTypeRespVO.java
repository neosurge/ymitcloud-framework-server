package com.ymit.module.system.controller.admin.dict.vo.type;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 字典类型信息 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class DictTypeRespVO {
    /**
     * 字典类型编号
     */
    @ExcelProperty("字典主键")
    private Long id;
    /**
     * 字典名称
     */
    @ExcelProperty("字典名称")
    private String name;
    /**
     * 字典类型
     */
    @ExcelProperty("字典类型")
    private String type;
    /**
     * 状态，参见 CommonStatusEnum 枚举类
     */
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
