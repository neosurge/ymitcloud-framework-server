package com.ymit.module.system.controller.admin.sms.vo.template;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 短信模板创建/修改 Request VO
 */
@Data
public class SmsTemplateSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 短信类型，参见 SmsTemplateTypeEnum 枚举类
     */
    @NotNull(message = "短信类型不能为空")
    private Integer type;
    /**
     * 开启状态，参见 CommonStatusEnum 枚举类
     */
    @NotNull(message = "开启状态不能为空")
    private Integer status;
    /**
     * 模板编码
     */
    @NotNull(message = "模板编码不能为空")
    private String code;
    /**
     * 模板名称
     */
    @NotNull(message = "模板名称不能为空")
    private String name;
    /**
     * 模板内容
     */
    @NotNull(message = "模板内容不能为空")
    private String content;
    /**
     * 备注
     */
    private String remark;
    /**
     * 短信 API 的模板编号
     */
    @NotNull(message = "短信 API 的模板编号不能为空")
    private String apiTemplateId;
    /**
     * 短信渠道编号
     */
    @NotNull(message = "短信渠道编号不能为空")
    private Long channelId;

}
