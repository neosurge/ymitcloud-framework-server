package com.ymitcloud.module.trade.controller.admin.order.vo;




import lombok.Data;

import jakarta.validation.constraints.NotNull;


/** 
 * 管理后台 - 订单改价 Request VO
 */
@Data
public class TradeOrderUpdatePriceReqVO {

    /** 订单编号*/
    @NotNull(message = "订单编号不能为空")
    private Long id;

    /** 订单调价，单位：分。正数，加价；负数，减价*/

    @NotNull(message = "订单调价价格不能为空")
    private Integer adjustPrice;

}
