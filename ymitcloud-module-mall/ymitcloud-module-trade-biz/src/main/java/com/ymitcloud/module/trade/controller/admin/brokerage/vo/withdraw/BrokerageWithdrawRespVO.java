package com.ymitcloud.module.trade.controller.admin.brokerage.vo.withdraw;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 佣金提现 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BrokerageWithdrawRespVO extends BrokerageWithdrawBaseVO {


    /** 编号*/
    private Integer id;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 用户昵称*/

    private String userNickname;

}
