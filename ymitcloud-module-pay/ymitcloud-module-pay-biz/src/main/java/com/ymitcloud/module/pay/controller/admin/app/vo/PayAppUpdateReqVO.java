package com.ymitcloud.module.pay.controller.admin.app.vo;


import lombok.*;
import jakarta.validation.constraints.*;

/** 管理后台 - 支付应用信息更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayAppUpdateReqVO extends PayAppBaseVO {


    /** 应用编号*/

    @NotNull(message = "应用编号不能为空")
    private Long id;

}
