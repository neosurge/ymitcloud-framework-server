package com.ymitcloud.module.trade.controller.admin.order.vo;

import com.ymitcloud.module.trade.controller.admin.base.member.user.MemberUserRespVO;
import com.ymitcloud.module.trade.controller.admin.base.product.property.ProductPropertyValueDetailRespVO;



import lombok.Data;

import java.util.List;


/** 管理后台 - 交易订单的分页项 */
@Data
public class TradeOrderPageItemRespVO extends TradeOrderBaseVO {

    /** 收件人地区名字*/
    private String receiverAreaName;

    /** 订单项列表*/
    private List<Item> items;

    /** 用户信息*/
    private MemberUserRespVO user;

    /** 
     * 推广人信息
     */
    private MemberUserRespVO brokerageUser;

    /** 
     * 管理后台 - 交易订单的分页项的订单项目
     */
    @Data
    public static class Item extends TradeOrderItemBaseVO {

        /** 属性列表*/

        private List<ProductPropertyValueDetailRespVO> properties;

    }

}
