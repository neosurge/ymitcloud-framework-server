package com.ymitcloud.module.infra.controller.admin.logger.vo.apiaccesslog;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - API 访问日志分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiAccessLogPageReqVO extends PageParam {

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
