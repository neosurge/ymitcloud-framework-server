package com.ymitcloud.module.system.controller.admin.tenant.vo.tenant;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 管理后台 - 租户创建/修改 Request VO
 */
@Data
public class TenantSaveReqVO {

    /**
     * 租户编号
     */
    private Long id;

    /**
     * 租户名
     */
    @NotNull(message = "租户名不能为空")
    private String name;

    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空")
    private String contactName;

    /**
     * 联系手机
     */
    private String contactMobile;

    /**
     * 租户状态
     */
    @NotNull(message = "租户状态")
    private Integer status;

    /**
     * 绑定域名
     */
    private String website;

    /**
     * 租户套餐编号
     */
    @NotNull(message = "租户套餐编号不能为空")
    private Long packageId;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空")
    private LocalDateTime expireTime;

    /**
     * 账号数量
     */
    @NotNull(message = "账号数量不能为空")
    private Integer accountCount;

    // ========== 仅【创建】时，需要传递的字段 ==========

    /**
     * 用户账号
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    /**
     * 密码
     */
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @AssertTrue(message = "用户账号、密码不能为空")
    @JsonIgnore
    public boolean isUsernameValid() {
        return id != null // 修改时，不需要传递
                || (ObjectUtil.isAllNotEmpty(username, password)); // 新增时，必须都传递 username、password
    }

}
