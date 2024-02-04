package com.ymitcloud.module.member.controller.admin.user.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 会员用户更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberUserUpdateReqVO extends MemberUserBaseVO {


    /** 编号*/

    @NotNull(message = "编号不能为空")
    private Long id;

}
