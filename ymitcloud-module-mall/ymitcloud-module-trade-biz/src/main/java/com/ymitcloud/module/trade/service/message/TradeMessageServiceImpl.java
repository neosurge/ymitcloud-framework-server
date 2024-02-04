package com.ymitcloud.module.trade.service.message;

import com.ymitcloud.module.system.api.notify.NotifyMessageSendApi;
import com.ymitcloud.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import com.ymitcloud.module.trade.enums.MessageTemplateConstants;
import com.ymitcloud.module.trade.service.message.bo.TradeOrderMessageWhenDeliveryOrderReqBO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Trade 消息 service 实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class TradeMessageServiceImpl implements TradeMessageService {

    @Resource
    private NotifyMessageSendApi notifyMessageSendApi;

    @Override
    public void sendMessageWhenDeliveryOrder(TradeOrderMessageWhenDeliveryOrderReqBO reqBO) {
        if (true) {
            return;
        }
        // 1、构造消息
        Map<String, Object> msgMap = new HashMap<>(2);
        msgMap.put("orderId", reqBO.getOrderId());
        msgMap.put("deliveryMessage", reqBO.getMessage());

        // TODO 云码：看下模版

        // 2、发送站内信
        notifyMessageSendApi.sendSingleMessageToMember(
                new NotifySendSingleToUserReqDTO()
                        .setUserId(reqBO.getUserId())
                        .setTemplateCode(MessageTemplateConstants.ORDER_DELIVERY)
                        .setTemplateParams(msgMap));
    }

}
