package com.ymitcloud.module.pay.controller.admin.demo.vo.transfer;

import com.ymitcloud.framework.common.util.validation.ValidationUtils;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.pay.core.enums.transfer.PayTransferTypeEnum;

import com.ymitcloud.module.pay.enums.transfer.PayTransferTypeEnum.Alipay;
import com.ymitcloud.module.pay.enums.transfer.PayTransferTypeEnum.WxPay;


import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;


/**
 * @author jason
 */

/** 管理后台 - 示例转账单创建 Request VO */
@Data
public class PayDemoTransferCreateReqVO {

    /** 转账类型*/

    @NotNull(message = "转账类型不能为空")
    @InEnum(PayTransferTypeEnum.class)
    private Integer type;


    /** 转账金额*/

    @NotNull(message = "转账金额不能为空")
    @Min(value = 1, message = "转账金额必须大于零")
    private Integer price;


    /** 收款人姓名*/

    @NotBlank(message = "收款人姓名不能为空", groups = {Alipay.class})
    private String userName;

    // ========== 支付宝转账相关字段 ==========

    /** 支付宝登录号,支持邮箱和手机号格式*/

    @NotBlank(message = "支付宝登录号不能为空", groups = {Alipay.class})
    private String alipayLogonId;

    // ========== 微信转账相关字段 ==========

    /** 微信 openId */

    @NotBlank(message = "微信 openId 不能为空", groups = {WxPay.class})
    private String openid;


    // ========== 转账到银行卡和钱包相关字段 待补充 ==========

    public void validate(Validator validator) {
        PayTransferTypeEnum transferType = PayTransferTypeEnum.typeOf(type);
        switch (transferType) {
            case ALIPAY_BALANCE: {
                ValidationUtils.validate(validator, this, Alipay.class);
                break;
            }
            case WX_BALANCE: {
                ValidationUtils.validate(validator, this, WxPay.class);
                break;
            }
            default: {
                throw new UnsupportedOperationException("待实现");
            }
        }
    }

}
