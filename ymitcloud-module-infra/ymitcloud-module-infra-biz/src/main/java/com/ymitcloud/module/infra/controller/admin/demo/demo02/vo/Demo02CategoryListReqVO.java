package com.ymitcloud.module.infra.controller.admin.demo.demo02.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 管理后台 - 示例分类列表 Request VO
 */
@Data
public class Demo02CategoryListReqVO {

    /**
     * 名字
     */
    private String name;

    /**
     * 父级编号
     */
    private Long parentId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}