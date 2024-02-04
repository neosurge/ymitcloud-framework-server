package com.ymitcloud.module.trade.job.order;

import com.ymitcloud.framework.quartz.core.handler.JobHandler;
import com.ymitcloud.framework.tenant.core.job.TenantJob;
import com.ymitcloud.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 交易订单的自动收货 Job
 *

 * @author 

 */
@Component
public class TradeOrderAutoReceiveJob implements JobHandler {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = tradeOrderUpdateService.receiveOrderBySystem();
        return String.format("自动收货 %s 个", count);
    }

}
