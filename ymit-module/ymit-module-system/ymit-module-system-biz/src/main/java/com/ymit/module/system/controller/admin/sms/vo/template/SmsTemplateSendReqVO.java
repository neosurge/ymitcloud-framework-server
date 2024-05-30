package com.ymit.module.system.controller.admin.sms.vo.template;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 管理后台 - 短信模板的发送 Request VO
 */
@Data
public class SmsTemplateSendReqVO {
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;
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
