package com.ymit.module.system.controller.admin.sms.vo.log;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 短信日志分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SmsLogPageReqVO extends PageParam {
    /**
     * 短信渠道编号
     */
    private Long channelId;
    /**
     * 模板编号
     */
    private Long templateId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 发送状态，参见 SmsSendStatusEnum 枚举类
     */
    private Integer sendStatus;
    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sendTime;
    /**
     * 接收状态，参见 SmsReceiveStatusEnum 枚举类
     */
    private Integer receiveStatus;
    /**
     * 接收时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

}
