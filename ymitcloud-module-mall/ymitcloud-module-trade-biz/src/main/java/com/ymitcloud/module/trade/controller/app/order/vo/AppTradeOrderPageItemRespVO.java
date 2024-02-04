package com.ymitcloud.module.trade.controller.app.order.vo;

import com.ymitcloud.module.trade.controller.app.order.vo.item.AppTradeOrderItemRespVO;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/** 用户 App - 订单交易的分页项 */
@Data
public class AppTradeOrderPageItemRespVO {

    /** 订单编号*/
    private Long id;

    /** 订单流水号*/
    private String no;

    /** 订单类型*/
    private Integer type;

    /** 订单状态*/
    private Integer status;

    /** 购买的商品数量*/
    private Integer productCount;

    /** 是否评价*/
    private Boolean commentStatus;

    /** 创建时间*/

    private LocalDateTime createTime;

    // ========== 价格 + 支付基本信息 ==========


    /** 支付订单编号*/
    private Long payOrderId;

    /** 应付金额，单位：分*/

    private Integer payPrice;

    // ========== 收件 + 物流基本信息 ==========


    /** 配送方式*/

    private Integer deliveryType;

    /**
     * 订单项数组
     */
    private List<AppTradeOrderItemRespVO> items;

}
