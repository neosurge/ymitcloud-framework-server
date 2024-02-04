package com.ymitcloud.module.pay.controller.admin.refund.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 退款订单分页查询 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayRefundPageItemRespVO extends PayRefundBaseVO {


    /** 支付订单编号*/
    private Long id;

    /** 应用名称*/
    private String  appName;

    /** 创建时间*/

    private LocalDateTime createTime;

}
