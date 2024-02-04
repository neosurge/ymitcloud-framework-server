package com.ymitcloud.module.trade.controller.app.order.vo.item;

import com.ymitcloud.module.trade.controller.app.base.property.AppProductPropertyValueDetailRespVO;

import lombok.Data;

import java.util.List;

/** 用户 App - 订单交易项 */
@Data
public class AppTradeOrderItemRespVO {

    /** 编号 */
    private Long id;

    /** 订单编号 */
    private Long orderId;

    /** 商品 SPU 编号 */
    private Long spuId;
    /** 商品 SPU 名称 */
    private String spuName;

    /** 商品 SKU 编号 */

    private Long skuId;

    /**
     * 属性数组
     */
    private List<AppProductPropertyValueDetailRespVO> properties;

    /** 商品图片 */
    private String picUrl;

    /** 购买数量 */
    private Integer count;

    /** 是否评价 */

    private Boolean commentStatus;

    // ========== 价格 + 支付基本信息 ==========

    /** 商品原价（单） */
    private Integer price;

    /** 应付金额（总），单位：分 */

    private Integer payPrice;

    // ========== 营销基本信息 ==========

    // TODO 云码：在捉摸一下

    // ========== 售后基本信息 ==========

    /**
     * 售后编号", example = "1024") private Long afterSaleId;
     * 
     * /** 售后状态
     */

    private Integer afterSaleStatus;

}
