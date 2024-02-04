package com.ymitcloud.module.pay.controller.admin.refund.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 退款订单分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayRefundPageReqVO extends PageParam {


    /** 应用编号 */
    private Long appId;

    /** 渠道编码 */
    private String channelCode;

    /** 商户支付单号 */
    private String merchantOrderId;

    /** 商户退款单号 */
    private String merchantRefundId;

    /** 渠道支付单号 */
    private String channelOrderNo;

    /** 渠道退款单号 */
    private String channelRefundNo;

    /** 退款状态 */
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    /**
     * 创建时间
     */

    private LocalDateTime[] createTime;

}
