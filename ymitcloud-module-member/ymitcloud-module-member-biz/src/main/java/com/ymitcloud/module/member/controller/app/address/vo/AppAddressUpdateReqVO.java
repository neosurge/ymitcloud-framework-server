package com.ymitcloud.module.member.controller.app.address.vo;


import lombok.*;
import jakarta.validation.constraints.*;

/** 用户 APP - 用户收件地址更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppAddressUpdateReqVO extends AppAddressBaseVO {


    /** 编号*/

    @NotNull(message = "编号不能为空")
    private Long id;

}
