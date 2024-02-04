package com.ymitcloud.module.trade.controller.admin.aftersale.vo;


import java.util.List;


import com.ymitcloud.module.trade.controller.admin.aftersale.vo.log.AfterSaleLogRespVO;
import com.ymitcloud.module.trade.controller.admin.base.member.user.MemberUserRespVO;
import com.ymitcloud.module.trade.controller.admin.base.product.property.ProductPropertyValueDetailRespVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderBaseVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderItemBaseVO;


import lombok.Data;

/** 管理后台 - 售后订单的详情 */
@Data
public class AfterSaleDetailRespVO extends AfterSaleBaseVO {

    /** 售后编号*/

    private Long id;



    /**
     * 订单基本信息
     */
    private TradeOrderBaseVO order;
    /**
     * 订单项列表
     */
    private OrderItem orderItem;

    /**
     * 用户信息
     */
    private MemberUserRespVO user;

    /**
     * 售后日志
     */
    private List<AfterSaleLogRespVO> logs;


    /** 
     * 管理后台 - 交易订单的详情的订单项目
     */

    @Data
    public static class OrderItem extends TradeOrderItemBaseVO {

        /**
         * 属性数组
         */
        private List<ProductPropertyValueDetailRespVO> properties;

    }

}
