package com.ymit.framework.tenant.core.mq.rocketmq;

import com.ymit.framework.tenant.core.context.TenantContextHolder;
import com.ymit.framework.web.core.util.WebFrameworkUtils;
import org.apache.rocketmq.client.hook.SendMessageContext;
import org.apache.rocketmq.client.hook.SendMessageHook;

/**
 * RocketMQ 消息队列的多租户 {@link SendMessageHook} 实现类
 * <p>
 * Producer 发送消息时，将 {@link TenantContextHolder} 租户编号，添加到消息的 Header 中
 *
 * @author Y.S
 * @date 2024.05.19
 */
public class TenantRocketMQSendMessageHook implements SendMessageHook {
    @Override
    public String hookName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void sendMessageBefore(SendMessageContext sendMessageContext) {
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            return;
        }
        sendMessageContext.getMessage().putUserProperty(WebFrameworkUtils.HEADER_TENANT_ID, tenantId.toString());
    }

    @Override
    public void sendMessageAfter(SendMessageContext sendMessageContext) {
    }
}