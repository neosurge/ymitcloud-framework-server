package com.ymitcloud.module.pay.controller.admin.app.vo;




import lombok.Data;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 应用更新状态 Request VO */
@Data
public class PayAppUpdateStatusReqVO {

    /** 应用编号*/
    @NotNull(message = "应用编号不能为空")
    private Long id;

    /** 状态，见 SysCommonStatusEnum 枚举*/

    @NotNull(message = "状态不能为空")
    private Integer status;

}
