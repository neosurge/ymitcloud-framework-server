package com.ymitcloud.module.mp.controller.admin.open.vo;





import lombok.Data;

import jakarta.validation.constraints.NotEmpty;


/** 管理后台 - 公众号处理消息 Request VO */

@Data
public class MpOpenHandleMessageReqVO {

    public static final String ENCRYPT_TYPE_AES = "aes";


    /** 微信加密签名*/
    @NotEmpty(message = "微信加密签名不能为空")
    private String signature;

    /** 时间戳*/
    @NotEmpty(message = "时间戳不能为空")
    private String timestamp;

    /** 随机数*/
    @NotEmpty(message = "随机数不能为空")
    private String nonce;

    /** 粉丝 openid*/
    @NotEmpty(message = "粉丝 openid 不能为空")
    private String openid;

    /** 消息加密类型*/
    private String encrypt_type;

    /** 微信签名 */

    private String msg_signature;

}
