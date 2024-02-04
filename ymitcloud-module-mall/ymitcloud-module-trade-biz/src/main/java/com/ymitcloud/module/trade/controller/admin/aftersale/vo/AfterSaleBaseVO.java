package com.ymitcloud.module.trade.controller.admin.aftersale.vo;



import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
* 交易售后 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class AfterSaleBaseVO {


    /** 售后流水号*/
    @NotNull(message = "售后流水号不能为空")
    private String no;

    /** 售后状态*/
    @NotNull(message = "售后状态不能为空")
    private Integer status;

    /** 售后类型*/
    @NotNull(message = "售后类型不能为空")
    private Integer type;

    /** 售后方式*/
    @NotNull(message = "售后方式不能为空")
    private Integer way;

    /** 用户编号*/
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 申请原因*/
    @NotNull(message = "申请原因不能为空")
    private String applyReason;

    /** 
     * 补充描述
     */
    private String applyDescription;

    /** 
     * 补充凭证图片
     */
    private List<String> applyPicUrls;

    /** 订单编号*/
    @NotNull(message = "订单编号不能为空")
    private Long orderId;

    /** 订单流水号*/
    @NotNull(message = "订单流水号不能为空")
    private String orderNo;

    /** 订单项编号*/
    @NotNull(message = "订单项编号不能为空")
    private Long orderItemId;

    /** 商品 SPU 编号*/
    @NotNull(message = "商品 SPU 编号不能为空")
    private Long spuId;

    /** 商品 SPU 名称*/
    @NotNull(message = "商品 SPU 名称不能为空")
    private String spuName;

    /** 商品 SKU 编号*/
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;

    /** 
     * 商品图片
     */
    private String picUrl;

    /** 购买数量*/
    @NotNull(message = "购买数量不能为空")
    private Integer count;

    /** 
     * 审批时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime auditTime;

    /** 
     * 审批人
     */
    private Long auditUserId;

    /** 
     * 审批备注
     */
    private String auditReason;

    /** 退款金额，单位：分*/
    @NotNull(message = "退款金额，单位：分不能为空")
    private Integer refundPrice;

    /** 
     * 支付退款编号
     */
    private Long payRefundId;

    /** 
     * 退款时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime refundTime;

    /**
     *  退货物流公司编号
     */
    private Long logisticsId;

    /**
     * 退货物流单号
     */
    private String logisticsNo;

    /** 
     * 退货时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deliveryTime;

    /** 
     * 收货时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime receiveTime;

    /**
     * 收货备注
     */

    private String receiveReason;

}
