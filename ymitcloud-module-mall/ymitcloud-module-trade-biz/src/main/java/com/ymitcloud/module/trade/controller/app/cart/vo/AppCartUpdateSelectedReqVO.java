package com.ymitcloud.module.trade.controller.app.cart.vo;



import java.util.Collection;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 用户 App - 购物车更新是否选中 Request VO
 */
@Data
public class AppCartUpdateSelectedReqVO {

    /** 编号列表*/
    @NotNull(message = "编号列表不能为空")
    private Collection<Long> ids;

    /** 是否选中*/

    @NotNull(message = "是否选中不能为空")
    private Boolean selected;

}
