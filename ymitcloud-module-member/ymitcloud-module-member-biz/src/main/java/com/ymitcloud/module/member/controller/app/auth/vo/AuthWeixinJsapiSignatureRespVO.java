package com.ymitcloud.module.member.controller.app.auth.vo;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/** 用户 APP - 微信公众号 JSAPI 签名 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthWeixinJsapiSignatureRespVO {


    /** 微信公众号的 appId*/
    private String appId;

    /** 匿名串*/
    private String nonceStr;

    /** 时间戳*/
    private Long timestamp;

    /** URL*/
    private String url;

    /** 签名*/

    private String signature;

}
