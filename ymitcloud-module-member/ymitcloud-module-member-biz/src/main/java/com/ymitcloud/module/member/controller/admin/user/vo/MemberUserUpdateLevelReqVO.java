package com.ymitcloud.module.member.controller.admin.user.vo;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** 管理后台 - 用户修改等级 Request VO */
@Data
@ToString(callSuper = true)
public class MemberUserUpdateLevelReqVO {


    /** 用户编号 */
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /**
     * 用户等级编号
     */
    private Long levelId;

    /** 修改原因 */
    @NotBlank(message = "修改原因不能为空")
    private String reason;

}
