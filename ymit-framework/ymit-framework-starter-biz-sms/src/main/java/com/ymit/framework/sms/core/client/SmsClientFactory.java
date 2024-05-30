package com.ymit.framework.sms.core.client;

import com.ymit.framework.sms.core.property.SmsChannelProperties;

/**
 * 短信客户端的工厂接口
 *
 * @author Y.S
 * @date 2024.05.23
 */
public interface SmsClientFactory {
    /**
     * 获得短信 Client
     *
     * @param channelId 渠道编号
     * @return 短信 Client
     */
    SmsClient getSmsClient(Long channelId);

    /**
     * 获得短信 Client
     *
     * @param channelCode 渠道编码
     * @return 短信 Client
     */
    SmsClient getSmsClient(String channelCode);

    /**
     * 创建短信 Client
     *
     * @param properties 配置对象
     */
    void createOrUpdateSmsClient(SmsChannelProperties properties);
}
