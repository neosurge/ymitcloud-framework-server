package com.ymitcloud.module.pay.controller.admin.order.vo;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**

 * 支付订单 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 *
 * @author aquan
 */
@Data
public class PayOrderBaseVO {


    /** 应用编号 */
    @NotNull(message = "应用编号不能为空")
    private Long appId;

    /** 渠道编号 */
    private Long channelId;

    /** 渠道编码 */
    private String channelCode;

    /** 商户订单编号 */
    @NotNull(message = "商户订单编号不能为空")
    private String merchantOrderId;

    /** 商品标题 */
    @NotNull(message = "商品标题不能为空")
    private String subject;

    /** 商品描述 */
    @NotNull(message = "商品描述不能为空")
    private String body;

    /** 异步通知地址 */
    @NotNull(message = "异步通知地址不能为空")
    private String notifyUrl;

    /** 支付金额，单位：分 */
    @NotNull(message = "支付金额，单位：分不能为空")
    private Long price;

    /** 渠道手续费，单位：百分比 */
    private Double channelFeeRate;

    /** 渠道手续金额，单位：分 */
    private Integer channelFeePrice;

    /** 支付状态 */
    @NotNull(message = "支付状态不能为空")
    private Integer status;

    /** 用户 IP */
    @NotNull(message = "用户 IP不能为空")
    private String userIp;

    /** 订单失效时间 */

    @NotNull(message = "订单失效时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime expireTime;


    /**
     * 订单支付成功时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime successTime;

    /** 支付成功的订单拓展单编号 */
    private Long extensionId;

    /** 支付订单号 */
    private String no;

    /** 退款总金额，单位：分 */
    @NotNull(message = "退款总金额，单位：分不能为空")
    private Long refundPrice;

    /** 渠道用户编号 */
    private String channelUserId;

    /** 渠道订单号 */

    private String channelOrderNo;

}
