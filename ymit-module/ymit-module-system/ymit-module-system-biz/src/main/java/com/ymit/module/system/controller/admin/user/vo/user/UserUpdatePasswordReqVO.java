package com.ymit.module.system.controller.admin.user.vo.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 管理后台 - 用户更新密码 Request VO
 */
@Data
public class UserUpdatePasswordReqVO {
    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long id;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

}
