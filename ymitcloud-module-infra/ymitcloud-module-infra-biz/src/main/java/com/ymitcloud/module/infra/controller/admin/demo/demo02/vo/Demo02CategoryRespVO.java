package com.ymitcloud.module.infra.controller.admin.demo.demo02.vo;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 管理后台 - 示例分类 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class Demo02CategoryRespVO {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Long id;
    /**
     * 名字
     */
    @ExcelProperty("名字")
    private String name;
    /**
     * 父级编号
     */
    @ExcelProperty("父级编号")
    private Long parentId;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}