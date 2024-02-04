package com.ymitcloud.module.trade.controller.app.config.vo;




import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;


/** 用户 App - 交易配置 */
@Data
public class AppTradeConfigRespVO {

    /** 腾讯地图 KEY*/

    private String tencentLbsKey;

    // ========== 配送相关 ==========


    /** 是否开启自提*/

    @NotNull(message = "是否开启自提不能为空")
    private Boolean deliveryPickUpEnabled;

    // ========== 售后相关 ==========


    /** 售后的退款理由*/
    private List<String> afterSaleRefundReasons;

    /** 售后的退货理由*/

    private List<String> afterSaleReturnReasons;

    // ========== 分销相关 ==========


    /** 分销海报地址数组*/
    private List<String> brokeragePosterUrls;

    /** 佣金冻结时间（天）*/
    private Integer brokerageFrozenDays;

    /** 佣金提现最小金额，单位：分*/
    private Integer brokerageWithdrawMinPrice;

    /** 提现方式*/

    private List<Integer> brokerageWithdrawTypes;

}
