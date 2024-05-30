package com.ymit.module.system.controller.admin.mail.vo.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 邮箱账号创建/修改 Request VO
 */
@Data
public class MailAccountSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "必须是 Email 格式")
    private String mail;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotNull(message = "密码必填")
    private String password;
    /**
     * SMTP 服务器域名
     */
    @NotNull(message = "SMTP 服务器域名不能为空")
    private String host;
    /**
     * SMTP 服务器端口
     */
    @NotNull(message = "SMTP 服务器端口不能为空")
    private Integer port;
    /**
     * 是否开启 ssl
     */
    @NotNull(message = "是否开启 ssl 必填")
    private Boolean sslEnable;

}
