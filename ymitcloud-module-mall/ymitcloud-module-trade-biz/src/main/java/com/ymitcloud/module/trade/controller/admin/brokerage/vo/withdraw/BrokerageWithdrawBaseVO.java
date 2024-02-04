package com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw;




import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 佣金提现 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BrokerageWithdrawBaseVO {


    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 提现金额*/
    @NotNull(message = "提现金额不能为空")
    private Integer price;

    /** 提现手续费*/
    @NotNull(message = "提现手续费不能为空")
    private Integer feePrice;

    /** 当前总佣金*/
    @NotNull(message = "当前总佣金不能为空")
    private Integer totalPrice;

    /** 提现类型*/
    @NotNull(message = "提现类型不能为空")
    private Integer type;

    /** 真实姓名*/
    private String name;

    /** 账号*/
    private String accountNo;

    /** 银行名称*/
    private String bankName;

    /** 开户地址*/
    private String bankAddress;

    /** 收款码*/
    private String accountQrCodeUrl;

    /** 状态*/
    @NotNull(message = "状态不能为空")
    private Integer status;

    /** 审核驳回原因*/
    private String auditReason;

    /** 
     * 审核时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime auditTime;

    /** 备注*/

    private String remark;

}
