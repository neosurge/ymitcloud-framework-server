package com.ymitcloud.module.pay.controller.admin.channel.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 管理后台 - 支付渠道 创建 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayChannelCreateReqVO extends PayChannelBaseVO {


    /** 渠道编码 */
    @NotNull(message = "渠道编码不能为空")
    private String code;

    /**
     * 渠道配置的 json 字符串
     */

    @NotBlank(message = "渠道配置不能为空")
    private String config;

}
