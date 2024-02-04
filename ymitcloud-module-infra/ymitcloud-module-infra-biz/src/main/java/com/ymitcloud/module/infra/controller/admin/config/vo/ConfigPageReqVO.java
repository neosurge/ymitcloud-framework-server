package com.ymitcloud.module.infra.controller.admin.config.vo;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 参数配置分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConfigPageReqVO extends PageParam {
    /**
     * 数据源名称，模糊匹配
     */
    private String name;
    /**
     * 参数键名，模糊匹配
     */
    private String key;
    /**
     * 参数类型，参见 SysConfigTypeEnum 枚举
     */
    private Integer type;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
