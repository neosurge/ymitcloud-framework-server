package com.ymitcloud.module.system.controller.admin.sms.vo.channel;

import lombok.Data;

/**
 * 管理后台 - 短信渠道精简 Response VO
 */
@Data
public class SmsChannelSimpleRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 短信签名
     */
    private String signature;
    /**
     * 渠道编码，参见 SmsChannelEnum 枚举类
     */
    private String code;

}
