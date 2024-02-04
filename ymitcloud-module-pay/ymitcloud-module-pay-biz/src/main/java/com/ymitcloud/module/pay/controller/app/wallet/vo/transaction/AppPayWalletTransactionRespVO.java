package com.ymitcloud.module.pay.controller.app.wallet.vo.transaction;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 APP - 钱包流水分页 */
@Data
public class AppPayWalletTransactionRespVO {

    /** 业务分类*/
    private Integer bizType;

    /** 交易金额，单位分*/
    private Long price;

    /** 流水标题*/
    private String title;

    /** 交易时间*/

    private LocalDateTime createTime;

}
