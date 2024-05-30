package com.ymit.module.system.controller.admin.dict.vo.data;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 字典数据信息 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class DictDataRespVO {
    /**
     * 字典数据编号
     */
    @ExcelProperty("字典编码")
    private Long id;
    /**
     * 显示顺序不能为空
     */
    @ExcelProperty("字典排序")
    private Integer sort;
    /**
     * 字典标签
     */
    @ExcelProperty("字典标签")
    private String label;
    /**
     * 字典值
     */
    @ExcelProperty("字典键值")
    private String value;
    /**
     * 字典类型
     */
    @ExcelProperty("字典类型")
    private String dictType;
    /**
     * 状态,见 CommonStatusEnum 枚举
     */
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 颜色类型,default、primary、success、info、warning、danger
     */
    private String colorType;
    /**
     * css 样式
     */
    private String cssClass;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
