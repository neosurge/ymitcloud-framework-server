package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;

import com.ymitcloud.framework.common.pojo.PageParam;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 应用 App - 绑定推广员 Request VO
 */
@Data
public class AppBrokerageUserBindReqVO extends PageParam {

    /** 推广员编号*/

    @NotNull(message = "推广员编号不能为空")
    private Long bindUserId;

}
