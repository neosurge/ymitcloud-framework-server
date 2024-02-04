package com.ymitcloud.module.system.controller.admin.user.vo.user;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymitcloud.framework.common.validation.Mobile;

import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 管理后台 - 用户创建/修改 Request VO
 */
@Data
public class UserSaveReqVO {
    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;
    /**
     * 用户昵称
     */
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickname;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 岗位编号数组
     */
    private Set<Long> postIds;
    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;
    /**
     * 手机号码
     */
    @Mobile
    private String mobile;
    /**
     * 用户性别，参见 SexEnum 枚举类
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;

    // ========== 仅【创建】时，需要传递的字段 ==========
    /**
     * 密码
     */
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @AssertTrue(message = "密码不能为空")
    @JsonIgnore
    public boolean isPasswordValid() {
        return id != null // 修改时，不需要传递
                || (ObjectUtil.isAllNotEmpty(password)); // 新增时，必须都传递 password
    }

}
