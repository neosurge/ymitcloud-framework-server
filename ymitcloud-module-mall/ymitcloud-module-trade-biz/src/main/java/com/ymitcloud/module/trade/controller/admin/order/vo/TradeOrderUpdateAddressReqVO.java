package com.ymitcloud.module.trade.controller.admin.order.vo;




import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/** 
 * 管理后台 - 订单修改地址 Request VO
 */
@Data
public class TradeOrderUpdateAddressReqVO {

    /** 订单编号*/
    @NotNull(message = "订单编号不能为空")
    private Long id;

    /** 收件人名称*/
    @NotEmpty(message = "收件人名称不能为空")
    private String receiverName;

    /** 收件人手机*/
    @NotEmpty(message = "收件人手机不能为空")
    private String receiverMobile;

    /** 收件人地区编号*/
    @NotNull(message = "收件人地区编号不能为空")
    private Integer receiverAreaId;

    /** 收件人详细地址*/

    @NotEmpty(message = "收件人详细地址不能为空")
    private String receiverDetailAddress;

}
