package com.ymitcloud.module.infra.controller.admin.demo.demo01.vo;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;

import lombok.Data;

/**
 * 管理后台 - 示例联系人 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class Demo01ContactRespVO {
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
     * 性别
     */
    @ExcelProperty(value = "性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer sex;

    /**
     * 出生年
     */
    @ExcelProperty("出生年")
    private LocalDateTime birthday;

    /**
     * 简介
     */
    @ExcelProperty("简介")
    private String description;

    /**
     * 头像
     */
    @ExcelProperty("头像")
    private String avatar;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}