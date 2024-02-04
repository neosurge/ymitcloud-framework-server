package com.ymitcloud.module.trade.controller.app.cart.vo;

import java.util.List;

import com.ymitcloud.module.trade.controller.app.base.sku.AppProductSkuBaseRespVO;

import lombok.Data;

/** 用户 App - 用户的购物车明细 */

@Data
public class AppCartDetailRespVO {

    /**
     * 商品分组数组
     */
    private List<ItemGroup> itemGroups;

    /**
     * 费用
     */
    private Order order;

    /**
     * 商品分组
     */

    @Data
    public static class ItemGroup {

        /**
         * 商品数组
         */
        private List<Sku> items;
        /**
         * 营销活动，订单级别
         */
        private Promotion promotion;

    }

    /**
     * 商品 SKU
     */

    @Data
    public static class Sku extends AppProductSkuBaseRespVO {

        /**
         * SPU 信息
         */
        private AppProductSkuBaseRespVO spu;

        // ========== 购物车相关的字段 ==========

        /** 商品数量 */
        private Integer count;
        /** 是否选中 */

        private Boolean selected;

        // ========== 价格相关的字段，对应 PriceCalculateRespDTO.OrderItem 的属性 ==========

        // TODO 云码：后续可以去除一些无用的字段

        /** 商品原价（单） */
        private Integer originalPrice;
        /** 商品原价（总） */
        private Integer totalOriginalPrice;
        /** 商品级优惠（总） */
        private Integer totalPromotionPrice;
        /** 最终购买金额（总） */
        private Integer totalPresentPrice;
        /** 最终购买金额（单） */
        private Integer presentPrice;
        /** 应付金额（总） */

        private Integer totalPayPrice;

        // ========== 营销相关的字段 ==========
        /**
         * 营销活动，商品级别
         */
        private Promotion promotion;

    }

    /**
     * 订单
     */
    @Data
    public static class Order {

        // TODO 云码：后续可以去除一些无用的字段

        /** 商品原价（总） */
        private Integer skuOriginalPrice;
        /** 商品优惠（总） */
        private Integer skuPromotionPrice;
        /** 订单优惠（总） */
        private Integer orderPromotionPrice;
        /** 运费金额 */
        private Integer deliveryPrice;
        /** 应付金额（总） */

        private Integer payPrice;

    }

    /**
     * 营销活动
     */
    @Data
    public static class Promotion {

        /** 营销编号 */ // 营销活动的编号、优惠劵的编号
        private Long id;
        /** 营销名字 */
        private String name;
        /** 营销类型 */
        private Integer type;

        // ========== 匹配情况 ==========
        /** 是否满足优惠条件 */
        private Boolean meet;
        /** 满足条件的提示 */

        private String meetTip;

    }

}
