package com.ymitcloud.module.trade.controller.admin.order.vo;

import com.ymitcloud.module.trade.controller.admin.base.member.user.MemberUserRespVO;
import com.ymitcloud.module.trade.controller.admin.base.product.property.ProductPropertyValueDetailRespVO;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/** 管理后台 - 交易订单的详情 */

@Data
public class TradeOrderDetailRespVO extends TradeOrderBaseVO {

    /**
     * 订单项列表
     */
    private List<Item> items;

    /**
     * 下单用户信息
     */
    private MemberUserRespVO user;
    /**
     * 推广用户信息
     */
    private MemberUserRespVO brokerageUser;

    /**
     * 操作日志列表
     */
    private List<OrderLog> logs;


    /** 收件人地区名字*/
    private String receiverAreaName;

    /** 
     * 管理后台 - 交易订单的操作日志
     */
    @Data
    public static class OrderLog {

        /** 操作详情*/
        private String content;

        /** 创建时间*/
        private LocalDateTime createTime;

        /** 用户类型*/

        private Integer userType;

    }


    /** 
     * 管理后台 - 交易订单的详情的订单项目
     */

    @Data
    public static class Item extends TradeOrderItemBaseVO {

        /**
         * 属性数组
         */
        private List<ProductPropertyValueDetailRespVO> properties;

    }

}
