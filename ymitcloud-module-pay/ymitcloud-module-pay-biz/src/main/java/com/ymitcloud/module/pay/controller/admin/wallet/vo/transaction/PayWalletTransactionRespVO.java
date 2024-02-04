package com.ymitcloud.module.pay.controller.admin.wallet.vo.transaction;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 APP - 钱包流水分页 */
@Data
public class PayWalletTransactionRespVO {

    /** 编号*/
    private Long id;

    /** 钱包编号*/
    private Long walletId;

    /** 业务分类*/
    private Integer bizType;

    /** 交易金额，单位分*/
    private Long price;

    /** 流水标题*/
    private String title;

    /** 交易后的余额，单位分*/
    private Long balance;

    /** 交易时间*/

    private LocalDateTime createTime;

    // TODO @jason：merchantOrderId 字段，需要在 PayWalletTransaction 存储下；然后，前端也返回下这个字段，界面也展示下商户名

}
