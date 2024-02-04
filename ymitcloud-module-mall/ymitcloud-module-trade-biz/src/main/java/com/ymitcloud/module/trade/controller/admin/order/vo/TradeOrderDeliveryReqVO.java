package com.ymitcloud.module.trade.controller.admin.order.vo;



import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *  管理后台 - 订单发货 Request VO
 */
@Data
public class TradeOrderDeliveryReqVO {

    /** 订单编号*/
    @NotNull(message = "订单编号不能为空")
    private Long id;

    /** 
     * 发货物流公司编号
     */
    @NotNull(message = "发货物流公司不能为空")
    private Long logisticsId;

    /** 
     * 发货物流单号
     */

    private String logisticsNo;

}
