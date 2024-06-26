package com.ymit.module.system.controller.admin.logger.vo.operatelog;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 操作日志分页列表 Request VO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperateLogPageReqVO extends PageParam {

    /**
     * 操作模块，模拟匹配
     */
    private String module;
    /**
     * 用户昵称，模拟匹配
     */
    private String userNickname;
    /**
     * 操作分类，参见 OperateLogTypeEnum 枚举类
     */
    private Integer type;
    /**
     * 操作状态
     */
    private Boolean success;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

}
