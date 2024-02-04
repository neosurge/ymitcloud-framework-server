package com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet;




import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 用户钱包 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class PayWalletBaseVO {


    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 用户类型*/
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /** 余额，单位分*/
    @NotNull(message = "余额，单位分不能为空")
    private Integer balance;

    /** 累计支出，单位分*/
    @NotNull(message = "累计支出，单位分不能为空")
    private Integer totalExpense;

    /** 累计充值，单位分*/
    @NotNull(message = "累计充值，单位分不能为空")
    private Integer totalRecharge;

    /** 冻结金额，单位分*/

    @NotNull(message = "冻结金额，单位分不能为空")
    private Integer freezePrice;

}