package com.ymit.module.system.controller.admin.mail.vo.log;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 邮箱日志分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MailLogPageReqVO extends PageParam {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型，参见 UserTypeEnum 枚举
     */
    private Integer userType;
    /**
     * 接收邮箱地址，模糊匹配
     */
    private String toMail;
    /**
     * 邮箱账号编号
     */
    private Long accountId;
    /**
     * 模板编号
     */
    private Long templateId;
    /**
     * 发送状态，参见 MailSendStatusEnum 枚举
     */
    private Integer sendStatus;
    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sendTime;

}
