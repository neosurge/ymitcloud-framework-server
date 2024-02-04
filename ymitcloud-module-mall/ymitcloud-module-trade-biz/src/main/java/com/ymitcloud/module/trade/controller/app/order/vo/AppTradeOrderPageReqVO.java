package com.ymitcloud.module.trade.controller.app.order.vo;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.order.TradeOrderStatusEnum;


import lombok.Data;

/**
 *  交易订单分页 Request VO=
 */
@Data
public class AppTradeOrderPageReqVO extends PageParam {

    /** 订单状态*/
    @InEnum(value = TradeOrderStatusEnum.class, message = "订单状态必须是 {value}")
    private Integer status;

    /** 是否评价 */

    private Boolean commentStatus;

}
