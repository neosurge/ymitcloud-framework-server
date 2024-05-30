package com.ymit.module.infra.controller.admin.config.vo;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 参数配置分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConfigPageReqVO extends PageParam {
    @Serial
    private static final long serialVersionUID = -3861846698290150395L;
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
