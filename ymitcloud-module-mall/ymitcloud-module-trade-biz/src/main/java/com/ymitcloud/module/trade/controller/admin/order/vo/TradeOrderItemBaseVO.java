package com.ymitcloud.module.trade.controller.admin.order.vo;

import lombok.Data;

/**
 * 交易订单项 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TradeOrderItemBaseVO {

    // ========== 订单项基本信息 ==========

    /** 编号 */
    private Long id;

    /** 用户编号 */
    private Long userId;

    /** 订单编号 */

    private Long orderId;

    // ========== 商品基本信息 ==========

    /** 商品 SPU 编号 */
    private Long spuId;

    /** 商品 SPU 名称 */
    private String spuName;

    /** 商品 SKU 编号 */
    private Long skuId;

    /** 商品图片 */
    private String picUrl;

    /** 购买数量 */

    private Integer count;

    // ========== 价格 + 支付基本信息 ==========

    /** 商品原价（单） */
    private Integer price;

    /** 商品优惠（总） */
    private Integer discountPrice;

    /** 商品实付金额（总） */
    private Integer payPrice;

    /** 子订单分摊金额（总） */
    private Integer orderPartPrice;

    /** 分摊后子订单实付金额（总） */

    private Integer orderDividePrice;

    // ========== 营销基本信息 ==========

    // TODO 云码：在捉摸一下

    // ========== 售后基本信息 ==========

    /** 售后状态 */

    private Integer afterSaleStatus;

}
