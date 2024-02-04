package com.ymitcloud.module.pay.controller.admin.order.vo;


import java.util.Map;


import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

/** 管理后台 - 支付订单提交 Request VO */
@Data
public class PayOrderSubmitReqVO {

    /** 支付单编号 */
    @NotNull(message = "支付单编号不能为空")
    private Long id;

    /** 支付渠道 */
    @NotEmpty(message = "支付渠道不能为空")
    private String channelCode;

    /**
     * 支付渠道的额外参数，例如说，微信公众号需要传递 openid 参数
     */
    private Map<String, String> channelExtras;

    /**
     * 展示模式
     */
    private String displayMode;

    /**
     * 回跳地址
     */

    @URL(message = "回跳地址的格式必须是 URL")
    private String returnUrl;

}
