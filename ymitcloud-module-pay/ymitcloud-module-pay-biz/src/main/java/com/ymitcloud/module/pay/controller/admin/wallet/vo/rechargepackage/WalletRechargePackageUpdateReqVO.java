package com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 充值套餐更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WalletRechargePackageUpdateReqVO extends WalletRechargePackageBaseVO {


    /** 编号*/

    @NotNull(message = "编号不能为空")
    private Long id;

}
