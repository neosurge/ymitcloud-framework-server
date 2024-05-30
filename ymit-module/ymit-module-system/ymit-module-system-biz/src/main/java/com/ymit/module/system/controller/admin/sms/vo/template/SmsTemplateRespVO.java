package com.ymit.module.system.controller.admin.sms.vo.template;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理后台 - 短信模板 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class SmsTemplateRespVO {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Long id;
    /**
     * 短信类型，参见 SmsTemplateTypeEnum 枚举类
     */
    @ExcelProperty(value = "短信签名", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SMS_TEMPLATE_TYPE)
    private Integer type;
    /**
     * 开启状态，参见 CommonStatusEnum 枚举类
     */
    @ExcelProperty(value = "开启状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 模板编码
     */
    @ExcelProperty("模板编码")
    private String code;
    /**
     * 模板名称
     */
    @ExcelProperty("模板名称")
    private String name;
    /**
     * 模板内容
     */
    @ExcelProperty("模板内容")
    private String content;
    /**
     * 参数数组
     */
    private List<String> params;
    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;
    /**
     * 短信 API 的模板编号
     */
    @ExcelProperty("短信 API 的模板编号")
    private String apiTemplateId;
    /**
     * 短信渠道编号
     */
    @ExcelProperty("短信渠道编号")
    private Long channelId;
    /**
     * 短信渠道编码
     */
    @ExcelProperty(value = "短信渠道编码", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SMS_CHANNEL_CODE)
    private String channelCode;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
