package com.ymitcloud.module.trade.controller.app.order.vo;

import com.ymitcloud.module.trade.controller.app.order.vo.item.AppTradeOrderItemRespVO;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/** 用户 App - 订单交易的明细 */

@Data
public class AppTradeOrderDetailRespVO {

    // ========== 订单基本信息 ==========


    /** 订单编号*/
    private Long id;

    /** 订单流水号*/
    private String no;

    /** 下单时间*/
    private LocalDateTime createTime;

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

    /** 是否评价*/

    private Boolean commentStatus;

    // ========== 价格 + 支付基本信息 ==========


    /** 是否已支付*/
    private Boolean payStatus;

    /** 支付订单编号*/
    private Long payOrderId;

    /** 
     * 付款时间
     */
    private LocalDateTime payTime;

    /** 付款超时时间*/
    private LocalDateTime payExpireTime;

    /** 
     * 支付渠道
     */
    private String payChannelCode;
    /** 
     * 支付渠道名
     */
    private String payChannelName;

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


    /** 配送方式*/
    private Integer deliveryType;

    /** 发货物流公司编号*/
    private Long logisticsId;

    /** 发货物流名称*/
    private String logisticsName;

    /** 发货物流单号*/
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

    /** 收件人地区名字*/
    private String receiverAreaName;

    /** 收件人详细地址*/
    private String receiverDetailAddress;

    /** 自提门店编号*/
    private Long pickUpStoreId;

    /** 自提核销码*/

    private String pickUpVerifyCode;

    // ========== 售后基本信息 ==========

    // ========== 营销基本信息 ==========


    /** 优惠劵编号*/
    private Long couponId;

    /** 优惠劵减免金额*/
    private Integer couponPrice;

    /** 积分抵扣的金额*/

    private Integer pointPrice;

    /**
     * 订单项数组
     */
    private List<AppTradeOrderItemRespVO> items;

}
