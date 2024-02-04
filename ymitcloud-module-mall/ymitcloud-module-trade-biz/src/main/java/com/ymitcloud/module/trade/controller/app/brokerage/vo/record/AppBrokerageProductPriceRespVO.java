package com.ymitcloud.module.trade.controller.app.brokerage.vo.record;



import lombok.Data;

/** 用户 App - 商品的分销金额 */
@Data
public class AppBrokerageProductPriceRespVO {

    /** 是否开启*/
    private Boolean enabled;

    /** 分销最小金额，单位：分*/
    private Integer brokerageMinPrice;

    /** 分销最大金额，单位：分*/

    private Integer brokerageMaxPrice;

}
