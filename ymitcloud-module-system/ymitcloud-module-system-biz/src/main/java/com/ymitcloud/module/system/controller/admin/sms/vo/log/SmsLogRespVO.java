package com.ymitcloud.module.system.controller.admin.sms.vo.log;

import java.time.LocalDateTime;
import java.util.Map;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;
import com.ymitcloud.framework.excel.core.convert.JsonConvert;
import com.ymitcloud.module.system.enums.DictTypeConstants;

import lombok.Data;

/**
 * 管理后台 - 短信日志 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class SmsLogRespVO {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Long id;
    /**
     * 短信渠道编号
     */
    @ExcelProperty("短信渠道编号")
    private Long channelId;
    /**
     * 短信渠道编码
     */
    @ExcelProperty("短信渠道编码")
    private String channelCode;
    /**
     * 模板编号
     */
    @ExcelProperty("模板编号")
    private Long templateId;
    /**
     * 模板编码
     */
    @ExcelProperty("模板编码")
    private String templateCode;
    /**
     * 短信类型
     */
    @ExcelProperty(value = "短信类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SMS_TEMPLATE_TYPE)
    private Integer templateType;
    /**
     * 短信内容
     */
    @ExcelProperty("短信内容")
    private String templateContent;
    /**
     * 短信参数
     */
    @ExcelProperty(value = "短信参数", converter = JsonConvert.class)
    private Map<String, Object> templateParams;
    /**
     * 短信 API 的模板编号
     */
    @ExcelProperty("短信 API 的模板编号")
    private String apiTemplateId;
    /**
     * 手机号
     */
    @ExcelProperty("手机号")
    private String mobile;
    /**
     * 用户编号
     */
    @ExcelProperty("用户编号")
    private Long userId;
    /**
     * 用户类型
     */
    @ExcelProperty(value = "用户类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.USER_TYPE)
    private Integer userType;
    /**
     * 发送状态
     */
    @ExcelProperty(value = "发送状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SMS_SEND_STATUS)
    private Integer sendStatus;
    /**
     * 发送时间
     */
    @ExcelProperty("发送时间")
    private LocalDateTime sendTime;

    /**
     * 短信 API 发送结果的编码
     */
    @ExcelProperty("短信 API 发送结果的编码")
    private String apiSendCode;
    /**
     * 短信 API 发送失败的提示
     */
    @ExcelProperty("短信 API 发送失败的提示")
    private String apiSendMsg;
    /**
     * 短信 API 发送返回的唯一请求 ID
     */
    @ExcelProperty("短信 API 发送返回的唯一请求 ID")
    private String apiRequestId;
    /**
     * 短信 API 发送返回的序号
     */
    @ExcelProperty("短信 API 发送返回的序号")
    private String apiSerialNo;
    /**
     * 接收状态
     */
    @ExcelProperty(value = "接收状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SMS_RECEIVE_STATUS)
    private Integer receiveStatus;
    /**
     * 接收时间
     */
    @ExcelProperty("接收时间")
    private LocalDateTime receiveTime;
    /**
     * API 接收结果的编码
     */
    @ExcelProperty("API 接收结果的编码")
    private String apiReceiveCode;
    /**
     * API 接收结果的说明
     */
    @ExcelProperty("API 接收结果的说明")
    private String apiReceiveMsg;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
