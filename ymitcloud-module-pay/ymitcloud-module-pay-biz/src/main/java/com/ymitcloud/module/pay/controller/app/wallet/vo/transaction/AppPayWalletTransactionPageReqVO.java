package com.ymitcloud.module.pay.controller.app.wallet.vo.transaction;

import com.ymitcloud.framework.common.pojo.PageParam;


import lombok.Data;

/** 用户 APP - 钱包流水分页 Request VO */

@Data
public class AppPayWalletTransactionPageReqVO extends PageParam {

    /**
     * 类型 - 收入
     */
    public static final Integer TYPE_INCOME = 1;
    /**
     * 类型 - 支出
     */
    public static final Integer TYPE_EXPENSE = 2;


    /** 
     * 类型
     */

    private Integer type;

}
