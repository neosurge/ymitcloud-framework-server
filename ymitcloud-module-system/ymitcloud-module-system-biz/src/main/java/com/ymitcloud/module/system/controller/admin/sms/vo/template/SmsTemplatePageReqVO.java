package com.ymitcloud.module.system.controller.admin.sms.vo.template;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 短信模板分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SmsTemplatePageReqVO extends PageParam {
    /**
     * 短信签名
     */
    private Integer type;
    /**
     * 开启状态
     */
    private Integer status;
    /**
     * 模板编码，模糊匹配
     */
    private String code;
    /**
     * 模板内容，模糊匹配
     */
    private String content;
    /**
     * 短信 API 的模板编号，模糊匹配
     */
    private String apiTemplateId;
    /**
     * 短信渠道编号
     */
    private Long channelId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
