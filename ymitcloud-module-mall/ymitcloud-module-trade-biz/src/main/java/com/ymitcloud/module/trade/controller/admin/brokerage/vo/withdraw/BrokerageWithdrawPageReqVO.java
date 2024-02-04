package com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawTypeEnum;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 管理后台 - 佣金提现分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BrokerageWithdrawPageReqVO extends PageParam {


    /** 用户编号*/
    private Long userId;

    /** 提现类型*/
    @InEnum(value = BrokerageWithdrawTypeEnum.class, message = "提现类型必须是 {value}")
    private Integer type;

    /** 真实姓名*/
    private String name;

    /** 账号*/
    private String accountNo;

    /** 银行名称*/
    private String bankName;

    /** 状态*/
    @InEnum(value = BrokerageWithdrawStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

    /** 
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
