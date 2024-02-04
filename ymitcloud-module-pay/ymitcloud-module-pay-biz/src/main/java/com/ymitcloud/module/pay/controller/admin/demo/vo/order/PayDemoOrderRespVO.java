package com.ymitcloud.module.pay.controller.admin.demo.vo.order;




import lombok.*;

import java.time.LocalDateTime;

/**
* 示例订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PayDemoOrderRespVO {


    /** 订单编号*/
    private Long id;

    /** 用户编号*/
    private Long userId;

    /** 商品编号*/
    private Long spuId;

    /** 商家备注", example = "李四")
    private String spuName;

    /** 价格，单位：分*/
    private Integer price;

    /** 是否已支付*/
    private Boolean payStatus;

    /** 支付订单编号", example = "16863")
    private Long payOrderId;

    /** 订单支付时间")
    private LocalDateTime payTime;

    /** 支付渠道", example = "alipay_qr")
    private String payChannelCode;

    /** 支付退款编号", example = "23366")
    private Long payRefundId;

    /** 退款金额，单位：分*/
    private Integer refundPrice;

    /** 退款时间")
    private LocalDateTime refundTime;

    /** 创建时间*/

    private LocalDateTime createTime;

}
