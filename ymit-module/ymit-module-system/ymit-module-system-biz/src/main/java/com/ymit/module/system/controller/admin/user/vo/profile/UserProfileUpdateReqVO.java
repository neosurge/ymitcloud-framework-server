package com.ymit.module.system.controller.admin.user.vo.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 管理后台 - 用户个人信息更新 Request VO
 */
@Data
public class UserProfileUpdateReqVO {

    /**
     * 用户昵称
     */
    @Size(max = 30, message = "用户昵称长度不能超过 30 个字符")
    private String nickname;
    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;
    /**
     * 手机号码
     */
    @Length(min = 11, max = 11, message = "手机号长度必须 11 位")
    private String mobile;
    /**
     * 用户性别，参见 SexEnum 枚举类
     */
    private Integer sex;

}
