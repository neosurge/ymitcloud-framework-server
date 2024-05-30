package com.ymit.module.system.controller.admin.notify.vo.template;

import com.ymit.framework.common.enums.CommonStatusEnum;
import com.ymit.framework.common.validation.InEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 站内信模版创建/修改 Request VO
 */
@Data
public class NotifyTemplateSaveReqVO {
    /**
     * ID
     */
    private Long id;

    /**
     * 模版名称
     */
    @NotEmpty(message = "模版名称不能为空")
    private String name;
    /**
     * 模版编码
     */
    @NotNull(message = "模版编码不能为空")
    private String code;
    /**
     * 模版类型，对应 system_notify_template_type 字典
     */
    @NotNull(message = "模版类型不能为空")
    private Integer type;

    /**
     * 发送人名称
     */
    @NotEmpty(message = "发送人名称不能为空")
    private String nickname;

    /**
     * 模版内容
     */
    @NotEmpty(message = "模版内容不能为空")
    private String content;

    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
