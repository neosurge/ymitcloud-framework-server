package com.ymitcloud.module.trade.controller.app.order.vo;


import java.util.List;

import com.ymitcloud.module.trade.controller.app.base.property.AppProductPropertyValueDetailRespVO;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/** 用户 App - 交易订单结算信息 */
@Data
public class AppTradeOrderSettlementRespVO {

    /** 交易类型*/ // 对应 TradeOrderTypeEnum 枚举
    private Integer type;

    /** 购物项数组*/
    private List<Item> items;

    /** 费用*/
    private Price price;

    /** 收件地址*/
    private Address address;

    /** 已使用的积分*/
    private Integer usedPoint;

    /** 总积分*/
    private Integer totalPoint;

    /** 
     * 购物项
     */

    @Data
    public static class Item {

        // ========== SPU 信息 ==========


        /** 品类编号*/
        private Long categoryId;
        /** SPU 编号*/
        private Long spuId;
        /** SPU 名字*/

        private String spuName;

        // ========== SKU 信息 ==========


        /** SKU 编号*/
        private Integer skuId;
        /** 价格，单位：分*/
        private Integer price;
        /** 图片地址*/
        private String picUrl;

        /** 属性数组*/

        private List<AppProductPropertyValueDetailRespVO> properties;

        // ========== 购物车信息 ==========


        /** 购物车编号*/
        private Long cartId;

        /** 购买数量*/

        private Integer count;

    }


    /** 
     * 费用（合计）
     */

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Price {


        /** 商品原价（总），单位：分*/
        private Integer totalPrice;

        /** 订单优惠（总），单位：分*/
        private Integer discountPrice;

        /** 运费金额，单位：分*/
        private Integer deliveryPrice;

        /** 优惠劵减免金额，单位：分*/
        private Integer couponPrice;

        /** 积分抵扣的金额，单位：分*/
        private Integer pointPrice;

        /** VIP 减免金额，单位：分*/
        private Integer vipPrice;

        /** 实际支付金额（总），单位：分*/

        private Integer payPrice;

    }


    /** 
     * 地址信息
     */
    @Data
    public static class Address {

        /** 编号*/
        private Long id;

        /** 收件人名称*/
        private String name;

        /** 手机号*/
        private String mobile;

        /** 地区编号*/
        @NotNull(message = "地区编号不能为空")
        private Long areaId;
        /** 地区名字*/
        private String areaName;

        /** 详细地址*/
        private String detailAddress;

        /** 是否默认收件地址*/

        private Boolean defaultStatus;

    }

}
