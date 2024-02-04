package com.ymitcloud.module.pay.controller.admin.wallet.vo.wallet;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 用户钱包 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayWalletRespVO extends PayWalletBaseVO {


    /** 编号*/
    private Long id;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 用户昵称*/
    private String nickname;
    /** 用户头像*/

    private String avatar;

}
