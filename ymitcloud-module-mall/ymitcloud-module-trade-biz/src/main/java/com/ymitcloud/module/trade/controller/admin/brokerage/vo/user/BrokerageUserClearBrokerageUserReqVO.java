package com.ymitcloud.module.trade.controller.admin.brokerage.vo.user;



import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/**
 * 管理后台 - 分销用户 - 清除推广员 Request VO=
 */

@Data
@ToString(callSuper = true)
public class BrokerageUserClearBrokerageUserReqVO {


    /** 用户编号 */

    @NotNull(message = "用户编号不能为空")
    private Long id;

}
