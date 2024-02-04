package com.ymitcloud.module.pay.controller.admin.channel.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 支付渠道 更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayChannelUpdateReqVO extends PayChannelBaseVO {


    /** 商户编号 */
    @NotNull(message = "商户编号不能为空")
    private Long id;

    /**
     * 渠道配置的json字符串
     */

    @NotBlank(message = "渠道配置不能为空")
    private String config;

}
