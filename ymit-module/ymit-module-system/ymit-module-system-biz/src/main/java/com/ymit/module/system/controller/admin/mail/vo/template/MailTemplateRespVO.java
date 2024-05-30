package com.ymit.module.system.controller.admin.mail.vo.template;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理后台 - 邮件末班 Response VO
 */
@Data
public class MailTemplateRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 模版名称
     */
    private String name;
    /**
     * 模版编号
     */
    private String code;
    /**
     * 发送的邮箱账号编号
     */
    private Long accountId;
    /**
     * 发送人名称
     */
    private String nickname;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 参数数组
     */
    private List<String> params;
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
