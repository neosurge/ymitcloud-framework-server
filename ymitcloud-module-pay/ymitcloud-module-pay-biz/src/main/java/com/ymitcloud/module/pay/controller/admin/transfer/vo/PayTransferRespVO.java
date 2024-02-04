package com.ymitcloud.module.pay.controller.admin.transfer.vo;




import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;


/** 管理后台 - 转账单 */
@Data
public class PayTransferRespVO {

    /** 编号*/
    private Long id;

    /** 转账单号*/
    private String no;

    /** 应用编号*/
    private Long appId;

    /** 转账渠道编号*/
    private Long channelId;

    /** 转账渠道编码*/
    private String channelCode;

    /** 商户转账单编号*/
    private String merchantTransferId;

    /** 类型*/
    private Integer type;

    /** 转账状态*/
    private Integer status;

    /** 转账成功时间")
    private LocalDateTime successTime;

    /** 转账金额*/
    private Integer price;

    /** 转账标题*/
    private String subject;

    /** 收款人姓名", example = "王五")
    private String userName;

    /** 支付宝登录号", example = "29245")
    private String alipayLogonId;

    /** 微信 openId", example = "26589")
    private String openid;

    /** 异步通知商户地址*/
    private String notifyUrl;

    /** 用户 IP*/
    private String userIp;

    /** 渠道的额外参数")
    private Map<String, String> channelExtras;

    /** 渠道转账单号")
    private String channelTransferNo;

    /** 调用渠道的错误码")
    private String channelErrorCode;

    /** 调用渠道的错误提示")
    private String channelErrorMsg;

    /** 渠道的同步/异步通知的内容")
    private String channelNotifyData;

    /** 创建时间*/

    private LocalDateTime createTime;

}
