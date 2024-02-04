package com.ymitcloud.module.infra.controller.admin.file.vo.file;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 文件分页 Request VO 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilePageReqVO extends PageParam {

    /**
     * 文件路径，模糊匹配
     */
    private String path;

    /**
     * 文件类型，模糊匹配
     */
    private String type;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
