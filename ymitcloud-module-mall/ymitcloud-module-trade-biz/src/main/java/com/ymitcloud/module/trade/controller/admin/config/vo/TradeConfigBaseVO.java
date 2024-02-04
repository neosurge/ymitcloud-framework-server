package com.ymitcloud.module.trade.controller.admin.config.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageBindModeEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageEnabledConditionEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawTypeEnum;



import lombok.Data;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * 交易中心配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TradeConfigBaseVO {

    // ========== 售后相关 ==========


    /** 售后的退款理由*/
    @NotEmpty(message = "售后的退款理由不能为空")
    private List<String> afterSaleRefundReasons;

    /** 售后的退货理由*/

    @NotEmpty(message = "售后的退货理由不能为空")
    private List<String> afterSaleReturnReasons;

    // ========== 配送相关 ==========

    /**
     * 是否启用全场包邮
     */

    /** 是否启用全场包邮*/
    @NotNull(message = "是否启用全场包邮不能为空")
    private Boolean deliveryExpressFreeEnabled;

    /** 全场包邮的最小金额*/

    @NotNull(message = "全场包邮的最小金额不能为空")
    @PositiveOrZero(message = "全场包邮的最小金额不能是负数")
    private Integer deliveryExpressFreePrice;


    /** 是否开启自提*/

    @NotNull(message = "是否开启自提不能为空")
    private Boolean deliveryPickUpEnabled;

    // ========== 分销相关 ==========


    /** 是否启用分佣*/
    @NotNull(message = "是否启用分佣不能为空")
    private Boolean brokerageEnabled;

    /** 分佣模式*/

    @NotNull(message = "分佣模式不能为空")
    @InEnum(value = BrokerageEnabledConditionEnum.class, message = "分佣模式必须是 {value}")
    private Integer brokerageEnabledCondition;


    /** 分销关系绑定模式*/

    @NotNull(message = "分销关系绑定模式不能为空")
    @InEnum(value = BrokerageBindModeEnum.class, message = "分销关系绑定模式必须是 {value}")
    private Integer brokerageBindMode;


    /** 分销海报图地址数组*/
    private List<String> brokeragePosterUrls;

    /** 一级返佣比例*/

    @NotNull(message = "一级返佣比例不能为空")
    @Range(min = 0, max = 100, message = "一级返佣比例必须在 0 - 100 之间")
    private Integer brokerageFirstPercent;


    /** 二级返佣比例*/

    @NotNull(message = "二级返佣比例不能为空")
    @Range(min = 0, max = 100, message = "二级返佣比例必须在 0 - 100 之间")
    private Integer brokerageSecondPercent;


    /** 用户提现最低金额*/

    @NotNull(message = "用户提现最低金额不能为空")
    @PositiveOrZero(message = "用户提现最低金额不能是负数")
    private Integer brokerageWithdrawMinPrice;


    /** 用户提现手续费百分比*/

    @NotNull(message = "用户提现手续费百分比不能为空")
    @PositiveOrZero(message = "用户提现手续费百分比不能是负数")
    private Integer brokerageWithdrawFeePercent;


    /** 提现银行*/
    @NotEmpty(message = "提现银行不能为空")
    private List<Integer> brokerageBankNames;

    /** 佣金冻结时间(天)*/

    @NotNull(message = "佣金冻结时间(天)不能为空")
    @PositiveOrZero(message = "佣金冻结时间不能是负数")
    private Integer brokerageFrozenDays;


    /** 提现方式*/

    @NotEmpty(message = "提现方式不能为空")
    @InEnum(value = BrokerageWithdrawTypeEnum.class, message = "提现方式必须是 {value}")
    private List<Integer> brokerageWithdrawTypes;

}
