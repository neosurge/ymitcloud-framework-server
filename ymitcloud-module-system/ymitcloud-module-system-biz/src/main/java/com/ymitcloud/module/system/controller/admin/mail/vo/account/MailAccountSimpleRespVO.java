package com.ymitcloud.module.system.controller.admin.mail.vo.account;

import lombok.Data;

/**
 * 管理后台 - 邮箱账号的精简 Response VO
 */
@Data
public class MailAccountSimpleRespVO {
    /**
     * 邮箱编号
     */
    private Long id;
    /**
     * 邮箱
     */
    private String mail;

}
