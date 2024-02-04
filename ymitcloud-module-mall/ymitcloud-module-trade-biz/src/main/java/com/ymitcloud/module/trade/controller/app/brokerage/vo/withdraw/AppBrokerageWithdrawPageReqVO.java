package com.ymitcloud.module.trade.controller.app.brokerage.vo.withdraw;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawTypeEnum;


import lombok.Data;

/**
 *  应用 App - 分销提现分页 Request VO
 */
@Data
public class AppBrokerageWithdrawPageReqVO extends PageParam {

    /** 类型*/
    @InEnum(value = BrokerageWithdrawTypeEnum.class, message = "类型必须是 {value}")
    private Integer type;

    /** 状态*/

    @InEnum(value = BrokerageWithdrawStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

}
