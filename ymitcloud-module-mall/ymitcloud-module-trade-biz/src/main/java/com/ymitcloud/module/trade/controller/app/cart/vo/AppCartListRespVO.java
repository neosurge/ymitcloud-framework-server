package com.ymitcloud.module.trade.controller.app.cart.vo;


import java.util.List;

import com.ymitcloud.module.trade.controller.app.base.sku.AppProductSkuBaseRespVO;
import com.ymitcloud.module.trade.controller.app.base.spu.AppProductSpuBaseRespVO;

import lombok.Data;

/** 用户 App - 用户的购物列表 */

@Data
public class AppCartListRespVO {

    /**
     * 有效的购物项数组
     */
    private List<Cart> validList;

    /**
     * 无效的购物项数组
     */
    private List<Cart> invalidList;


    /** 
     * 购物项
     */
    @Data
    public static class Cart {

        /** 购物项的编号*/
        private Long id;

        /** 商品数量*/
        private Integer count;

        /** 是否选中*/

        private Boolean selected;

        /**
         * 商品 SPU
         */
        private AppProductSpuBaseRespVO spu;
        /**
         * 商品 SKU
         */
        private AppProductSkuBaseRespVO sku;

    }

}
