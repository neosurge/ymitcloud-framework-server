package com.ymitcloud.module.trade.controller.admin.aftersale.vo;



import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 管理后台 - 交易售后拒绝收货 Request VO
 */
@Data
public class AfterSaleRefuseReqVO {

    /** 售后编号 */
    @NotNull(message = "售后编号不能为空")
    private Long id;

    /** 收货备注 */

    @NotNull(message = "收货备注不能为空")
    private String refuseMemo;

}
