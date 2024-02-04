package com.ymitcloud.module.trade.controller.app.order;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.pay.api.notify.dto.PayOrderNotifyReqDTO;

import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderCreateReqVO;
import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderCreateRespVO;
import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderDetailRespVO;
import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderPageItemRespVO;
import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderPageReqVO;
import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderSettlementReqVO;
import com.ymitcloud.module.trade.controller.app.order.vo.AppTradeOrderSettlementRespVO;

import com.ymitcloud.module.trade.controller.app.order.vo.item.AppTradeOrderItemCommentCreateReqVO;
import com.ymitcloud.module.trade.controller.app.order.vo.item.AppTradeOrderItemRespVO;
import com.ymitcloud.module.trade.convert.order.TradeOrderConvert;
import com.ymitcloud.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderDO;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.ymitcloud.module.trade.enums.order.TradeOrderStatusEnum;
import com.ymitcloud.module.trade.framework.order.config.TradeOrderProperties;
import com.ymitcloud.module.trade.service.delivery.DeliveryExpressService;
import com.ymitcloud.module.trade.service.order.TradeOrderQueryService;
import com.ymitcloud.module.trade.service.order.TradeOrderUpdateService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 App - 交易订单
 */

@RestController
@RequestMapping("/trade/order")
@Validated
@Slf4j
public class AppTradeOrderController {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;
    @Resource
    private DeliveryExpressService deliveryExpressService;

    @Resource
    private TradeOrderProperties tradeOrderProperties;


    /**
     * 获得订单结算信息
     * 
     * @param settlementReqVO
     * @return
     */
    @GetMapping("/settlement")
    @PreAuthenticated
    public CommonResult<AppTradeOrderSettlementRespVO> settlementOrder(
            @Valid AppTradeOrderSettlementReqVO settlementReqVO) {
        return success(tradeOrderUpdateService.settlementOrder(getLoginUserId(), settlementReqVO));
    }

    /**
     * 创建订单
     * 
     * @param createReqVO
     * @param terminal
     * @return
     */
    @PostMapping("/create")
    @PreAuthenticated
    public CommonResult<AppTradeOrderCreateRespVO> createOrder(@Valid @RequestBody AppTradeOrderCreateReqVO createReqVO,
            @RequestHeader Integer terminal) {
        TradeOrderDO order = tradeOrderUpdateService.createOrder(getLoginUserId(), getClientIP(), createReqVO,
                terminal);
        return success(new AppTradeOrderCreateRespVO().setId(order.getId()).setPayOrderId(order.getPayOrderId()));
    }

    /**
     * 更新订单为已支付
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-paid")

    public CommonResult<Boolean> updateOrderPaid(@RequestBody PayOrderNotifyReqDTO notifyReqDTO) {
        tradeOrderUpdateService.updateOrderPaid(Long.valueOf(notifyReqDTO.getMerchantOrderId()),
                notifyReqDTO.getPayOrderId());
        return success(true);
    }


    /**
     * 获得交易订单
     * 
     * @param id 交易订单编号
     * @return
     */
    @GetMapping("/get-detail")

    public CommonResult<AppTradeOrderDetailRespVO> getOrder(@RequestParam("id") Long id) {
        // 查询订单
        TradeOrderDO order = tradeOrderQueryService.getOrder(getLoginUserId(), id);
        if (order == null) {
            return success(null);
        }

        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(order.getId());
        // 查询物流公司

        DeliveryExpressDO express = order.getLogisticsId() != null && order.getLogisticsId() > 0
                ? deliveryExpressService.getDeliveryExpress(order.getLogisticsId())
                : null;

        // 最终组合
        return success(TradeOrderConvert.INSTANCE.convert02(order, orderItems, tradeOrderProperties, express));
    }


    /**
     * 获得交易订单的物流轨迹
     * 
     * @param id 交易订单编号
     * @return
     */
    @GetMapping("/get-express-track-list")
    public CommonResult<List<?>> getOrderExpressTrackList(@RequestParam("id") Long id) {
        return success(TradeOrderConvert.INSTANCE
                .convertList02(tradeOrderQueryService.getExpressTrackList(id, getLoginUserId())));
    }

    /**
     * 获得交易订单分页
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppTradeOrderPageItemRespVO>> getOrderPage(AppTradeOrderPageReqVO reqVO) {
        // 查询订单
        PageResult<TradeOrderDO> pageResult = tradeOrderQueryService.getOrderPage(getLoginUserId(), reqVO);
        // 查询订单项

        List<TradeOrderItemDO> orderItems = tradeOrderQueryService
                .getOrderItemListByOrderId(convertSet(pageResult.getList(), TradeOrderDO::getId));

        // 最终组合
        return success(TradeOrderConvert.INSTANCE.convertPage02(pageResult, orderItems));
    }


    /**
     * 获得交易订单数量
     * 
     * @return
     */
    @GetMapping("/get-count")

    public CommonResult<Map<String, Long>> getOrderCount() {
        Map<String, Long> orderCount = Maps.newLinkedHashMapWithExpectedSize(5);
        // 全部
        orderCount.put("allCount", tradeOrderQueryService.getOrderCount(getLoginUserId(), null, null));
        // 待付款（未支付）

        orderCount.put("unpaidCount",
                tradeOrderQueryService.getOrderCount(getLoginUserId(), TradeOrderStatusEnum.UNPAID.getStatus(), null));

        // 待发货
        orderCount.put("undeliveredCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.UNDELIVERED.getStatus(), null));
        // 待收货
        orderCount.put("deliveredCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.DELIVERED.getStatus(), null));
        // 待评价
        orderCount.put("uncommentedCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.COMPLETED.getStatus(), false));
        return success(orderCount);
    }


    /**
     * 确认交易订单收货
     * 
     * @param id 交易订单编号
     * @return
     */
    @PutMapping("/receive")

    public CommonResult<Boolean> receiveOrder(@RequestParam("id") Long id) {
        tradeOrderUpdateService.receiveOrderByMember(getLoginUserId(), id);
        return success(true);
    }


    /**
     * 取消交易订单
     * 
     * @param id 交易订单编号
     * @return
     */
    @DeleteMapping("/cancel")

    public CommonResult<Boolean> cancelOrder(@RequestParam("id") Long id) {
        tradeOrderUpdateService.cancelOrderByMember(getLoginUserId(), id);
        return success(true);
    }


    /**
     * 删除交易订单
     * 
     * @param id 交易订单编号
     * @return
     */
    @DeleteMapping("/delete")

    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        tradeOrderUpdateService.deleteOrder(getLoginUserId(), id);
        return success(true);
    }

    // ========== 订单项 ==========

    /**
     * 获得交易订单项
     * 
     * @param id 交易订单项编号
     * @return
     */
    @GetMapping("/item/get")

    public CommonResult<AppTradeOrderItemRespVO> getOrderItem(@RequestParam("id") Long id) {
        TradeOrderItemDO item = tradeOrderQueryService.getOrderItem(getLoginUserId(), id);
        return success(TradeOrderConvert.INSTANCE.convert03(item));
    }


    /**
     * 创建交易订单项的评价
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/item/create-comment")

    public CommonResult<Long> createOrderItemComment(@RequestBody AppTradeOrderItemCommentCreateReqVO createReqVO) {
        return success(tradeOrderUpdateService.createOrderItemCommentByMember(getLoginUserId(), createReqVO));
    }

}
