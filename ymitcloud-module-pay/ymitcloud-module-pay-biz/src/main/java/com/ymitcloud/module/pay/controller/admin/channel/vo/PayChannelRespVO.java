package com.ymitcloud.module.pay.controller.admin.channel.vo;



import lombok.*;

import java.time.LocalDateTime;


/** 管理后台 - 支付渠道 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayChannelRespVO extends PayChannelBaseVO {


    /** 商户编号*/
    private Long id;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 渠道编码*/
    private String code;

    /** 配置*/

    private String config;

}
