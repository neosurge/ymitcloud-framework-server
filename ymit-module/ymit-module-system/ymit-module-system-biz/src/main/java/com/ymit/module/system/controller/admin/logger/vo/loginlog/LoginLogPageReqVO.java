package com.ymit.module.system.controller.admin.logger.vo.loginlog;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 登录日志分页列表 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginLogPageReqVO extends PageParam {

    /**
     * 用户 IP，模拟匹配
     */
    private String userIp;

    /**
     * 用户账号，模拟匹配
     */
    private String username;

    /**
     * 操作状态
     */
    private Boolean status;

    /**
     * 登录时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
