package com.ymitcloud.module.pay.controller.admin.transfer.vo;

import lombok.*;



import com.ymitcloud.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/** 管理后台 - 转账单分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayTransferPageReqVO extends PageParam {


    /** 转账单号*/
    private String no;

    /** 应用编号*/
    private Long appId;

    /** 渠道编码*/
    private String channelCode;

    /** 商户转账单编号*/
    private String merchantTransferId;

    /** 类型*/
    private Integer type;

    /** 转账状态*/
    private Integer status;

    /** 收款人姓名*/
    private String userName;

    /** 渠道转账单号*/
    private String channelTransferNo;

    /** 创建时间 */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
