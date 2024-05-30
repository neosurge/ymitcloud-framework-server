package com.ymit.module.system.controller.admin.dept.vo.post;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 岗位信息 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class PostRespVO {
    /**
     * 岗位序号
     */
    @ExcelProperty("岗位序号")
    private Long id;
    /**
     * 岗位名称
     */
    @ExcelProperty("岗位名称")
    private String name;
    /**
     * 岗位编码
     */
    @ExcelProperty("岗位编码")
    private String code;
    /**
     * 显示顺序不能为空
     */
    @ExcelProperty("岗位排序")
    private Integer sort;
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
