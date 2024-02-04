package com.ymitcloud.module.pay.controller.admin.order.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 支付订单分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayOrderPageReqVO extends PageParam {


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
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)


    private LocalDateTime[] createTime;

}
