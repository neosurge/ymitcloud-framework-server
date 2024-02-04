package com.ymitcloud.module.trade.controller.app.aftersale.vo;



import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 用户 App - 交易售后退回货物 Request VO
 */
@Data
public class AppAfterSaleDeliveryReqVO {

    /** 售后编号*/
    @NotNull(message = "售后编号不能为空")
    private Long id;

    /** 退货物流公司编号*/
    @NotNull(message = "退货物流公司编号不能为空")
    private Long logisticsId;

    /** 退货物流单号*/

    @NotNull(message = "退货物流单号不能为空")
    private String logisticsNo;

}
