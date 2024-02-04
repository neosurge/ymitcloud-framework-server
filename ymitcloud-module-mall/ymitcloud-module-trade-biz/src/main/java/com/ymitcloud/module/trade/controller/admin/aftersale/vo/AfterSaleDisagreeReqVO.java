package com.ymitcloud.module.trade.controller.admin.aftersale.vo;



import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 * 管理后台 - 交易售后拒绝 Request VO
 */
@Data
public class AfterSaleDisagreeReqVO {

    /** 售后编号 */
    @NotNull(message = "售后编号不能为空")
    private Long id;

    /** 审批备注 */

    @NotEmpty(message = "审批备注不能为空")
    private String auditReason;

}
