package com.ymitcloud.module.pay.controller.admin.order.vo;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 支付订单分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayOrderPageItemRespVO extends PayOrderBaseVO {


    /** 支付订单编号 */
    private Long id;

    /** 创建时间 */
    private LocalDateTime createTime;

    /**
     * 应用名称
     */
    private String appName;


}
