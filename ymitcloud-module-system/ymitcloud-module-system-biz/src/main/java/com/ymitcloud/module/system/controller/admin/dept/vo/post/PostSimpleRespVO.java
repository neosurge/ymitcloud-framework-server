package com.ymitcloud.module.system.controller.admin.dept.vo.post;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 管理后台 - 岗位信息的精简 Response VO
 */
@Data
public class PostSimpleRespVO {
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

}
