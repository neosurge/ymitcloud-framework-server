package com.ymitcloud.module.pay.controller.admin.order.vo;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 支付订单详细信息 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayOrderDetailsRespVO extends PayOrderBaseVO {


    /** 支付订单编号 */
    private Long id;

    /** 应用名称 */
    private String appName;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */

    private LocalDateTime updateTime;

    /**
     * 支付订单扩展
     */
    private PayOrderExtension extension;


    /**
     * 支付订单扩展
     */
    @Data
    public static class PayOrderExtension {

        /** 支付订单号 */
        private String no;

        /**
         * 支付异步通知的内容
         */

        private String channelNotifyData;

    }

}
