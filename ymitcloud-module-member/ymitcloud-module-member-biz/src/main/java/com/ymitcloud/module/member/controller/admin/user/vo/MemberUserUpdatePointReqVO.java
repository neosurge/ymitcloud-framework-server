package com.ymitcloud.module.member.controller.admin.user.vo;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;

/** 管理后台 - 用户修改积分 Request VO */
@Data
@ToString(callSuper = true)
public class MemberUserUpdatePointReqVO {

    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /** 变动积分，正数为增加，负数为减少*/
    @NotNull(message = "变动积分不能为空")
    private Integer point;

}
