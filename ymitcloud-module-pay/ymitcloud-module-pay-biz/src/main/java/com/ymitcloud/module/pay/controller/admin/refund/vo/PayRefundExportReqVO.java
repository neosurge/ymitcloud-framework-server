package com.ymitcloud.module.pay.controller.admin.refund.vo;




import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/** 管理后台 - 退款订单 Excel 导出 Request VO，参数和 PayRefundPageReqVO 是一致的*/
@Data
public class PayRefundExportReqVO {

    /** 应用编号*/
    private Long appId;

    /** 渠道编码*/
    private String channelCode;

    /** 商户支付单号*/
    private String merchantOrderId;

    /** 商户退款单号*/
    private String merchantRefundId;

    /** 渠道支付单号*/
    private String channelOrderNo;

    /** 渠道退款单号*/
    private String channelRefundNo;

    /** 退款状态*/
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    /** 创建时间 */

    private LocalDateTime[] createTime;

}
