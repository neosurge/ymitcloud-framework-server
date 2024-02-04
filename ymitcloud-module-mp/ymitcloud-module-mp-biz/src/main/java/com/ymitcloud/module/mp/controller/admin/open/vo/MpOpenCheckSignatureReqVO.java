package com.ymitcloud.module.mp.controller.admin.open.vo;




import lombok.Data;

import jakarta.validation.constraints.NotEmpty;


/** 管理后台 - 公众号校验签名 Request VO */
@Data
public class MpOpenCheckSignatureReqVO {

    /** 微信加密签名*/
    @NotEmpty(message = "微信加密签名不能为空")
    private String signature;

    /** 时间戳*/
    @NotEmpty(message = "时间戳不能为空")
    private String timestamp;

    /** 随机数*/
    @NotEmpty(message = "随机数不能为空")
    private String nonce;

    /** 随机字符串*/

    @NotEmpty(message = "随机字符串不能为空")
    @SuppressWarnings("SpellCheckingInspection")
    private String echostr;

}
