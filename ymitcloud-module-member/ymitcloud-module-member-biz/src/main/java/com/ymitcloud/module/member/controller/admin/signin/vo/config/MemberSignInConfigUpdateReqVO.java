package com.ymitcloud.module.member.controller.admin.signin.vo.config;




import lombok.*;

import jakarta.validation.constraints.*;


/** 管理后台 - 签到规则更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberSignInConfigUpdateReqVO extends MemberSignInConfigBaseVO {


    /** 规则自增主键*/

    @NotNull(message = "规则自增主键不能为空")
    private Long id;

}
