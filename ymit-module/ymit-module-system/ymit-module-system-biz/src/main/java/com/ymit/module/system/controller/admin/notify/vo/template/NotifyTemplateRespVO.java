package com.ymit.module.system.controller.admin.notify.vo.template;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理后台 - 站内信模版 Response VO
 */
@Data
public class NotifyTemplateRespVO {
    /**
     * ID
     */
    private Long id;
    /**
     * 模版名称
     */
    private String name;
    /**
     * 模版编码
     */
    private String code;
    /**
     * 模版类型，对应 system_notify_template_type 字典
     */
    private Integer type;
    /**
     * 发送人名称
     */
    private String nickname;
    /**
     * 模版内容
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
