package com.ymitcloud.module.system.controller.admin.notify.vo.template;

import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 站内信模板的发送 Request VO
 */
@Data
public class NotifyTemplateSendReqVO {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /**
     * 模板编码
     */
    @NotEmpty(message = "模板编码不能为空")
    private String templateCode;
    /**
     * 模板参数
     */
    private Map<String, Object> templateParams;

}
