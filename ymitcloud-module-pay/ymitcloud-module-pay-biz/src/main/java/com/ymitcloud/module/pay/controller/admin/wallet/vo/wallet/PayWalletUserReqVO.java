package com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet;




import lombok.Data;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 用户钱包明细 Request VO */
@Data
public class PayWalletUserReqVO {

    /** 用户编号*/

    @NotNull(message = "用户编号不能为空")
    private Long userId;

}
