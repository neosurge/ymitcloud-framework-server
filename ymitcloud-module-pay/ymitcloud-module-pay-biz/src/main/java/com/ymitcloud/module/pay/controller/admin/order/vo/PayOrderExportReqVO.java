package com.ymitcloud.module.pay.controller.admin.order.vo;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * 管理后台 - 支付订单 Excel 导出 Request VO，参数和 PayOrderPageReqVO 是一致的
 */
@Data
public class PayOrderExportReqVO {

    /** 应用编号 */
    private Long appId;

    /** 渠道编码 */
    private String channelCode;

    /** 商户订单编号 */
    private String merchantOrderId;

    /** 渠道编号 */
    private String channelOrderNo;

    /** 支付单号 */
    private String no;

    /** 支付状态 */
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    /**
     * 创建时间
     */

    private LocalDateTime[] createTime;

}
