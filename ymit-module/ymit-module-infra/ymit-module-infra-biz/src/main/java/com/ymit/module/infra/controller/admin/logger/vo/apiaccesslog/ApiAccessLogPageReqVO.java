package com.ymit.module.infra.controller.admin.logger.vo.apiaccesslog;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - API 访问日志分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiAccessLogPageReqVO extends PageParam {

    @Serial
    private static final long serialVersionUID = 6531917275957273013L;
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 应用名
     */
    private String applicationName;
    /**
     * 请求地址，模糊匹配
     */
    private String requestUrl;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] beginTime;
    /**
     * 执行时长,大于等于，单位：毫秒
     */
    private Integer duration;
    /**
     * 结果码
     */
    private Integer resultCode;

}
