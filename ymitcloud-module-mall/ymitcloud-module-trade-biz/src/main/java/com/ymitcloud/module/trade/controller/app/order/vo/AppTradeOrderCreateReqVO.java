package com.ymitcloud.module.trade.controller.app.order.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

/** 
 * 用户 App - 交易订单创建 Request VO
 */
@Data
public class AppTradeOrderCreateReqVO extends AppTradeOrderSettlementReqVO {

    /** 备注*/

    private String remark;

    @AssertTrue(message = "配送方式不能为空")
    @JsonIgnore
    public boolean isDeliveryTypeNotNull() {
        return getDeliveryType() != null;
    }

}
