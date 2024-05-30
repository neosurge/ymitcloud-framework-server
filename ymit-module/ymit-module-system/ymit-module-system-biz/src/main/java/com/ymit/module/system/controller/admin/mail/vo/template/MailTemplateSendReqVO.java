package com.ymit.module.system.controller.admin.mail.vo.template;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 管理后台 - 邮件发送 Req VO
 */
@Data
public class MailTemplateSendReqVO {
    /**
     * 接收邮箱
     */
    @NotEmpty(message = "接收邮箱不能为空")
    private String mail;
    /**
     * 模板编码
     */
    @NotNull(message = "模板编码不能为空")
    private String templateCode;
    /**
     * 模板参数
     */
    private Map<String, Object> templateParams;

}
