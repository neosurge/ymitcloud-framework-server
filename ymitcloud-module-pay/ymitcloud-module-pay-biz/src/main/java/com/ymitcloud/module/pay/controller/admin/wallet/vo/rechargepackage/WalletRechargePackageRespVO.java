package com.ymitcloud.module.pay.controller.admin.wallet.vo.rechargepackage;



import lombok.*;
import java.time.LocalDateTime;

/** 管理后台 - 充值套餐 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WalletRechargePackageRespVO extends WalletRechargePackageBaseVO {


    /** 编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
