package com.ymitcloud.module.pay.controller.admin.refund.vo;



import java.time.LocalDateTime;

import lombok.Data;


/**
* 退款订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PayRefundBaseVO {


    /** 外部退款号*/
    private String no;

    /** 应用编号*/
    private Long appId;

    /** 渠道编号*/
    private Long channelId;

    /** 渠道编码*/
    private String channelCode;

    /** 订单编号*/

    private Long orderId;

    // ========== 商户相关字段 ==========


    /** 商户订单编号*/
    private String merchantOrderId;

    /** 商户退款订单号*/
    private String merchantRefundId;

    /** 异步通知地址*/

    private String notifyUrl;

    // ========== 退款相关字段 ==========


    /** 退款状态*/
    private Integer status;

    /** 支付金额*/
    private Long payPrice;

    /** 退款金额,单位分*/
    private Long refundPrice;

    /** 退款原因*/
    private String reason;

    /** 用户 IP*/

    private String userIp;

    // ========== 渠道相关字段 ==========


    /** 渠道订单号*/
    private String channelOrderNo;

    /** 渠道退款单号*/
    private String channelRefundNo;

    /** 退款成功时间*/
    private LocalDateTime successTime;

    /** 调用渠道的错误码*/
    private String channelErrorCode;

    /** 调用渠道的错误提示*/
    private String channelErrorMsg;

    /** 支付渠道的额外参数 */

    private String channelNotifyData;

}
