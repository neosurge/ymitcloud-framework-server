package com.ymit.module.system.controller.admin.mail.vo.template;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 邮件模版分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MailTemplatePageReqVO extends PageParam {
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    private Integer status;

    /**
     * 标识，模糊匹配
     */
    private String code;
    /**
     * 名称，模糊匹配
     */
    private String name;

    /**
     * 账号编号
     */
    private Long accountId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
