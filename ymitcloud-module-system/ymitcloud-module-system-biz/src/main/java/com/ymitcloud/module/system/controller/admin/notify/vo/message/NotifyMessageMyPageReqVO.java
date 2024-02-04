package com.ymitcloud.module.system.controller.admin.notify.vo.message;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 站内信分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotifyMessageMyPageReqVO extends PageParam {

    /**
     * 是否已读
     */
    private Boolean readStatus;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
