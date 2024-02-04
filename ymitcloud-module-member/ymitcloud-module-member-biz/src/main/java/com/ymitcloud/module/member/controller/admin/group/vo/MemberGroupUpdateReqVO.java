package com.ymitcloud.module.member.controller.admin.group.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 用户分组更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberGroupUpdateReqVO extends MemberGroupBaseVO {


    /** 编号*/

    @NotNull(message = "编号不能为空")
    private Long id;

}
