package com.ymitcloud.module.pay.controller.admin.refund.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 退款订单详情 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayRefundDetailsRespVO extends PayRefundBaseVO {


    /** 支付退款编号*/
    private Long id;

    /** 应用名称*/
    private String appName;

    /** 支付订单*/
    private Order order;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 更新时间*/
    private LocalDateTime updateTime;

    /** 管理后台 - 支付订单 */
    @Data
    public static class Order {

        /** 商品标题*/

        private String subject;

    }

}
