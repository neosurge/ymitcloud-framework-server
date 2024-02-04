package com.ymitcloud.module.trade.controller.app.brokerage.vo.withdraw;


import org.hibernate.validator.constraints.URL;

import com.ymitcloud.framework.common.util.validation.ValidationUtils;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawTypeEnum;


import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;

/** 
 * 用户 App - 分销提现创建 Request VO
 */
@Data
public class AppBrokerageWithdrawCreateReqVO {

    /** 提现方式*/
    @InEnum(value = BrokerageWithdrawTypeEnum.class, message = "提现方式必须是 {value}")
    private Integer type;

    /** 提现金额，单位：分*/

    @PositiveOrZero(message = "提现金额不能小于 0")
    @NotNull(message = "提现金额不能为空")
    private Integer price;

    // ========== 银行卡、微信、支付宝 提现相关字段 ==========


    /** 提现账号*/

    @NotBlank(message = "提现账号不能为空", groups = {Bank.class, Wechat.class, Alipay.class})
    private String accountNo;

    // ========== 微信、支付宝 提现相关字段 ==========


    /** 收款码的图片*/

    @URL(message = "收款码的图片，必须是一个 URL")
    private String accountQrCodeUrl;

    // ========== 银行卡 提现相关字段 ==========


    /** 持卡人姓名*/
    @NotBlank(message = "持卡人姓名不能为空", groups = {Bank.class})
    private String name;
    /** 提现银行*/
    @NotNull(message = "提现银行不能为空", groups = {Bank.class})
    private Integer bankName;
    /** 开户地址*/

    private String bankAddress;

    public interface Wallet {
    }

    public interface Bank {
    }

    public interface Wechat {
    }

    public interface Alipay {
    }

    public void validate(Validator validator) {
        if (BrokerageWithdrawTypeEnum.WALLET.getType().equals(type)) {
            ValidationUtils.validate(validator, this, Wallet.class);
        } else if (BrokerageWithdrawTypeEnum.BANK.getType().equals(type)) {
            ValidationUtils.validate(validator, this, Bank.class);
        } else if (BrokerageWithdrawTypeEnum.WECHAT.getType().equals(type)) {
            ValidationUtils.validate(validator, this, Wechat.class);
        } else if (BrokerageWithdrawTypeEnum.ALIPAY.getType().equals(type)) {
            ValidationUtils.validate(validator, this, Alipay.class);
        }
    }

}
