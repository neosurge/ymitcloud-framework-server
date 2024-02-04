package com.ymitcloud.module.system.controller.admin.mail.vo.account;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 邮箱账号 Response VO
 */
@Data
public class MailAccountRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * SMTP 服务器域名
     */
    private String host;
    /**
     * SMTP 服务器端口
     */
    private Integer port;
    /**
     * 是否开启 ssl
     */
    private Boolean sslEnable;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
