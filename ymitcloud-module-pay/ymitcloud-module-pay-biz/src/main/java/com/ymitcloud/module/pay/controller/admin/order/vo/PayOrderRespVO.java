package com.ymitcloud.module.pay.controller.admin.order.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 支付订单 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayOrderRespVO extends PayOrderBaseVO {


    /** 支付订单编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
