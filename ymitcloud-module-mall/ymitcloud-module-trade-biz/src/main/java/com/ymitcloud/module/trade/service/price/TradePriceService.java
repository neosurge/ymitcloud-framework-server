package com.ymitcloud.module.trade.service.price;

import com.ymitcloud.module.trade.service.price.bo.TradePriceCalculateReqBO;
import com.ymitcloud.module.trade.service.price.bo.TradePriceCalculateRespBO;

import jakarta.validation.Valid;

/**
 * 价格计算 Service 接口
 *

 * @author 

 */
public interface TradePriceService {

    /**
     * 价格计算
     *
     * @param calculateReqDTO 计算信息
     * @return 计算结果
     */
    TradePriceCalculateRespBO calculatePrice(@Valid TradePriceCalculateReqBO calculateReqDTO);

}
