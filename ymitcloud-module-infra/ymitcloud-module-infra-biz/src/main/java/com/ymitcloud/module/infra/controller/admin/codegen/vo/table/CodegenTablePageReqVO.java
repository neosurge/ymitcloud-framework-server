package com.ymitcloud.module.infra.controller.admin.codegen.vo.table;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 表定义分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CodegenTablePageReqVO extends PageParam {

    /**
     * 表名称，模糊匹配
     */
    private String tableName;

    /**
     * 表描述，模糊匹配
     */
    private String tableComment;

    /**
     * 实体，模糊匹配
     */
    private String className;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
