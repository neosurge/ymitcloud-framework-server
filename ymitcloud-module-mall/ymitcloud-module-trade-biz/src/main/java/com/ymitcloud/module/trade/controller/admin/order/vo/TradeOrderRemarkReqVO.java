package com.ymitcloud.module.trade.controller.admin.order.vo;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 管理后台 - 订单备注 Request VO
 */
@Data
public class TradeOrderRemarkReqVO {

    /** 订单编号*/
    @NotNull(message = "订单编号不能为空")
    private Long id;

    /**
     *  商家备注
     */

    @NotEmpty(message = "订单备注不能为空")
    private String remark;

}
