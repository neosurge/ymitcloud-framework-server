package com.ymit.module.system.controller.admin.notify.vo.message;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 管理后台 - 站内信 Response VO
 */
@Data
public class NotifyMessageRespVO {

    /**
     * ID
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
     * 模版编号
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
     * 模版内容
     */
    private String templateContent;

    /**
     * 模版类型
     */
    private Integer templateType;

    /**
     * 模版参数
     */
    private Map<String, Object> templateParams;

    /**
     * 是否已读
     */
    private Boolean readStatus;

    /**
     * 阅读时间
     */
    private LocalDateTime readTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
