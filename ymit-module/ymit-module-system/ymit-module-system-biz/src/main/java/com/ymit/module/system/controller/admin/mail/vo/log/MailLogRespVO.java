package com.ymit.module.system.controller.admin.mail.vo.log;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 管理后台 - 邮件日志 Response VO
 */
@Data
public class MailLogRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型，参见 UserTypeEnum 枚举
     */
    private Byte userType;
    /**
     * 接收邮箱地址
     */
    private String toMail;
    /**
     * 邮箱账号编号
     */
    private Long accountId;
    /**
     * 发送邮箱地址
     */
    private String fromMail;
    /**
     * 模板编号
     */
    private Long templateId;
    /**
     * 模板编码
     */
    private String templateCode;
    /**
     * 模版发送人名称
     */
    private String templateNickname;
    /**
     * 邮件标题
     */
    private String templateTitle;
    /**
     * 邮件内容
     */
    private String templateContent;
    /**
     * 邮件参数
     */
    private Map<String, Object> templateParams;
    /**
     * 发送状态，参见 MailSendStatusEnum 枚举
     */
    private Byte sendStatus;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
    /**
     * 发送返回的消息 ID
     */
    private String sendMessageId;
    /**
     * 发送异常
     */
    private String sendException;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
