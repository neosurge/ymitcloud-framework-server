package com.ymit.framework.sms.core.client.impl;

import com.ymit.framework.sms.core.client.SmsClient;
import com.ymit.framework.sms.core.property.SmsChannelProperties;

/**
 * 短信客户端的抽象类，提供模板方法，减少子类的冗余代码
 *
 * @author Y.S
 * @date 2024.05.23
 */
public abstract class AbstractSmsClient implements SmsClient {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractSmsClient.class);
    /**
     * 短信渠道配置
     */
    protected volatile SmsChannelProperties properties;

    public AbstractSmsClient(SmsChannelProperties properties) {
        this.properties = properties;
    }

    /**
     * 初始化
     */
    public final void init() {
        this.doInit();
        log.debug("[init][配置({}) 初始化完成]", this.properties);
    }

    /**
     * 自定义初始化
     */
    protected abstract void doInit();

    public final void refresh(SmsChannelProperties properties) {
        // 判断是否更新
        if (properties.equals(this.properties)) {
            return;
        }
        log.info("[refresh][配置({})发生变化，重新初始化]", properties);
        this.properties = properties;
        // 初始化
        this.init();
    }

    @Override
    public Long getId() {
        return this.properties.getId();
    }

}
