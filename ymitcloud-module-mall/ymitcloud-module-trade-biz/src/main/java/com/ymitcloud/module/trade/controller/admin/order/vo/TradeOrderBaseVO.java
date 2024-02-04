package com.ymitcloud.module.trade.controller.admin.order.vo;



import java.time.LocalDateTime;

import lombok.Data;


/**
 * 交易订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TradeOrderBaseVO {

    // ========== 订单基本信息 ==========


    /** 订单编号*/
    private Long id;

    /** 订单流水号*/
    private String no;

    /** 下单时间*/
    private LocalDateTime createTime;

    /** 订单类型*/
    private Integer type;

    /** 订单来源*/
    private Integer terminal;

    /** 用户编号*/
    private Long userId;

    /** 用户 IP*/
    private String userIp;

    /** 用户备注*/
    private String userRemark;

    /** 订单状态*/
    private Integer status;

    /** 购买的商品数量*/
    private Integer productCount;

    /** 订单完成时间")
    private LocalDateTime finishTime;

    /** 订单取消时间")
    private LocalDateTime cancelTime;

    /** 取消类型", example = "10")
    private Integer cancelType;

    /** 商家备注", example = "你猜一下")

    private String remark;

    // ========== 价格 + 支付基本信息 ==========


    /** 支付订单编号*/
    private Long payOrderId;

    /** 是否已支付*/
    private Boolean payStatus;

    /** 付款时间")
    private LocalDateTime payTime;

    /** 支付渠道*/
    private String payChannelCode;

    /** 商品原价（总）*/
    private Integer totalPrice;

    /** 订单优惠（总）*/
    private Integer discountPrice;

    /** 运费金额*/
    private Integer deliveryPrice;

    /** 订单调价（总）*/
    private Integer adjustPrice;

    /** 应付金额（总）*/

    private Integer payPrice;

    // ========== 收件 + 物流基本信息 ==========


    /** 配送方式", example = "10")
    private Integer deliveryType;

    /** 自提门店", example = "10")
    private Long pickUpStoreId;

    /** 自提核销码", example = "10")
    private Long pickUpVerifyCode;

    /** 配送模板编号", example = "1024")
    private Long deliveryTemplateId;

    /** 发货物流公司编号", example = "1024")
    private Long logisticsId;

    /** 发货物流单号", example = "1024")
    private String logisticsNo;

    /** 发货时间")
    private LocalDateTime deliveryTime;

    /** 收货时间")
    private LocalDateTime receiveTime;

    /** 收件人名称*/
    private String receiverName;

    /** 收件人手机*/
    private String receiverMobile;

    /** 收件人地区编号*/
    private Integer receiverAreaId;

    /** 收件人详细地址*/

    private String receiverDetailAddress;

    // ========== 售后基本信息 ==========


    /** 
     * 售后状态
     */
    private Integer afterSaleStatus;

    /** 退款金额*/

    private Integer refundPrice;

    // ========== 营销基本信息 ==========


    /** 
     * 优惠劵编号
     */
    private Long couponId;

    /** 优惠劵减免金额*/
    private Integer couponPrice;

    /** 积分抵扣的金额*/
    private Integer pointPrice;

    /** VIP 减免金额*/
    private Integer vipPrice;

    /** 
     * 推广人编号
     */

    private Long brokerageUserId;

}
