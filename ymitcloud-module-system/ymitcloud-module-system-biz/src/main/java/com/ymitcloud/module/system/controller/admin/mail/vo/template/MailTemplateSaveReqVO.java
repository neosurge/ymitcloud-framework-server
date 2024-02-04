package com.ymitcloud.module.system.controller.admin.mail.vo.template;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 邮件模版创建/修改 Request VO
 */
@Data
public class MailTemplateSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 模版名称
     */
    @NotNull(message = "名称不能为空")
    private String name;
    /**
     * 模版编号
     */
    @NotNull(message = "模版编号不能为空")
    private String code;
    /**
     * 发送的邮箱账号编号
     */
    @NotNull(message = "发送的邮箱账号编号不能为空")
    private Long accountId;
    /**
     * 发送人名称
     */
    private String nickname;
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    private String title;
    /**
     * 内容
     */
    @NotEmpty(message = "内容不能为空")
    private String content;
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
