package com.ymitcloud.module.pay.controller.admin.wallet.vo.transaction;

import com.ymitcloud.framework.common.pojo.PageParam;


import lombok.Data;

/** 管理后台 - 钱包流水分页 Request VO */
@Data
public class PayWalletTransactionPageReqVO extends PageParam  {

    /** 钱包编号*/

    private Long walletId;

}
