package com.ymit.module.system.controller.admin.mail.vo.template;

import lombok.Data;

/**
 * 管理后台 - 邮件模版的精简 Response VO
 */
@Data
public class MailTemplateSimpleRespVO {
    /**
     * 模版编号
     */
    private Long id;
    /**
     * 模版名字
     */
    private String name;

}
