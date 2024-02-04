package com.ymitcloud.module.trade.controller.app.aftersale.vo;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.trade.controller.app.base.property.AppProductPropertyValueDetailRespVO;

import lombok.Data;

/** 用户 App - 交易售后 */
@Data
public class AppAfterSaleRespVO {

    /** 售后编号*/
    private Long id;

    /** 售后流水号*/
    private String no;

    /** 售后状态*/
    private Integer status;

    /** 售后方式*/
    private Integer way;

    /** 售后类型*/
    private Integer type;

    /** 申请原因*/
    private String applyReason;

    /** 补充描述*/
    private String applyDescription;

    /** 补充凭证图片*/

    private List<String> applyPicUrls;

    // ========== 交易订单相关 ==========


    /** 交易订单编号*/
    private Long orderId;

    /** 交易订单流水号*/
    private String orderNo;

    /** 交易订单项编号*/
    private Long orderItemId;

    /** 商品 SPU 编号*/
    private Long spuId;

    /** 商品 SPU 名称*/
    private String spuName;

    /** 商品 SKU 编号*/

    private Long skuId;

    /**
     * 属性数组
     */
    private List<AppProductPropertyValueDetailRespVO> properties;


    /** 商品图片*/
    private String picUrl;

    /** 退货商品数量*/

    private Integer count;

    // ========== 审批相关 ==========

    /**
     * 审批备注
     *
     * 注意，只有审批不通过才会填写
     */
    private String auditReason;

    // ========== 退款相关 ==========


    /** 退款金额，单位：分*/
    private Integer refundPrice;

    /** 
     * 退款时间
     */

    private LocalDateTime refundTime;

    // ========== 退货相关 ==========


    /** 退货物流公司编号*/
    private Long logisticsId;

    /** 退货物流单号*/
    private String logisticsNo;

    /** 
     * 退货时间
     */
    private LocalDateTime deliveryTime;

    /** 
     * 收货时间
     */
    private LocalDateTime receiveTime;

    /** 
     * 收货备注
     */

    private String receiveReason;

}
