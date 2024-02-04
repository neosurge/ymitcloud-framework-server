package com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/** 
 * 管理后台 - 驳回申请 Request VO
 */

@Data
@ToString(callSuper = true)
public class BrokerageWithdrawRejectReqVO {


    /** 编号*/
    @NotNull(message = "编号不能为空")
    private Integer id;

    /** 
     * 审核驳回原因
     */

    @NotEmpty(message = "审核驳回原因不能为空")
    private String auditReason;

}
