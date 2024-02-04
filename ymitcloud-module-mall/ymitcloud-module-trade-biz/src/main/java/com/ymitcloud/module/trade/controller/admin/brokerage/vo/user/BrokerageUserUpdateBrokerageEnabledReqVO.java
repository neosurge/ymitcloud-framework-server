package com.ymitcloud.module.trade.controller.admin.brokerage.vo.user;



import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/** 
 * 管理后台 - 分销用户 - 修改推广员 Request VO
 */

@Data
@ToString(callSuper = true)
public class BrokerageUserUpdateBrokerageEnabledReqVO {


    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /** 推广资格*/

    @NotNull(message = "推广资格不能为空")
    private Boolean enabled;

}
