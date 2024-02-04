package com.ymitcloud.module.trade.controller.admin.order;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;

import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderDeliveryReqVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderDetailRespVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderPageItemRespVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderPageReqVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderRemarkReqVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderSummaryRespVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderUpdateAddressReqVO;
import com.ymitcloud.module.trade.controller.admin.order.vo.TradeOrderUpdatePriceReqVO;

import com.ymitcloud.module.trade.convert.order.TradeOrderConvert;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderDO;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderLogDO;
import com.ymitcloud.module.trade.service.order.TradeOrderLogService;
import com.ymitcloud.module.trade.service.order.TradeOrderQueryService;
import com.ymitcloud.module.trade.service.order.TradeOrderUpdateService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 交易订单
 */

@RestController
@RequestMapping("/trade/order")
@Validated
@Slf4j
public class TradeOrderController {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;
    @Resource
    private TradeOrderLogService tradeOrderLogService;

    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 获得交易订单分页
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<PageResult<TradeOrderPageItemRespVO>> getOrderPage(TradeOrderPageReqVO reqVO) {
        // 查询订单
        PageResult<TradeOrderDO> pageResult = tradeOrderQueryService.getOrderPage(reqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 查询用户信息
        Set<Long> userIds = CollUtil.unionDistinct(convertList(pageResult.getList(), TradeOrderDO::getUserId),
                convertList(pageResult.getList(), TradeOrderDO::getBrokerageUserId, Objects::nonNull));
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(userIds);
        // 查询订单项

        List<TradeOrderItemDO> orderItems = tradeOrderQueryService
                .getOrderItemListByOrderId(convertSet(pageResult.getList(), TradeOrderDO::getId));

        // 最终组合
        return success(TradeOrderConvert.INSTANCE.convertPage(pageResult, orderItems, userMap));
    }


    /**
     * 获得交易订单统计
     * 
     * @param reqVO
     * @return
     */
    @GetMapping("/summary")

    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<TradeOrderSummaryRespVO> getOrderSummary(TradeOrderPageReqVO reqVO) {
        return success(tradeOrderQueryService.getOrderSummary(reqVO));
    }


    /**
     * 获得交易订单详情
     * 
     * @param id 订单编号
     * @return
     */
    @GetMapping("/get-detail")

    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<TradeOrderDetailRespVO> getOrderDetail(@RequestParam("id") Long id) {
        // 查询订单
        TradeOrderDO order = tradeOrderQueryService.getOrder(id);
        if (order == null) {
            return success(null);
        }
        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(id);

        // 拼接数据
        MemberUserRespDTO user = memberUserApi.getUser(order.getUserId());

        MemberUserRespDTO brokerageUser = order.getBrokerageUserId() != null
                ? memberUserApi.getUser(order.getBrokerageUserId())
                : null;

        List<TradeOrderLogDO> orderLogs = tradeOrderLogService.getOrderLogListByOrderId(id);
        return success(TradeOrderConvert.INSTANCE.convert(order, orderItems, orderLogs, user, brokerageUser));
    }


    /**
     * 获得交易订单的物流轨迹
     * 
     * @param id 交易订单编号
     * @return
     */
    @GetMapping("/get-express-track-list")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<List<?>> getOrderExpressTrackList(@RequestParam("id") Long id) {
        return success(TradeOrderConvert.INSTANCE.convertList02(tradeOrderQueryService.getExpressTrackList(id)));
    }

    /**
     * 订单发货
     * 
     * @param deliveryReqVO
     * @return
     */
    @PutMapping("/delivery")

    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> deliveryOrder(@RequestBody TradeOrderDeliveryReqVO deliveryReqVO) {
        tradeOrderUpdateService.deliveryOrder(deliveryReqVO);
        return success(true);
    }


    /**
     * 订单备注
     */
    @PutMapping("/update-remark")

    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateOrderRemark(@RequestBody TradeOrderRemarkReqVO reqVO) {
        tradeOrderUpdateService.updateOrderRemark(reqVO);
        return success(true);
    }


    /**
     * 订单调价
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-price")

    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateOrderPrice(@RequestBody TradeOrderUpdatePriceReqVO reqVO) {
        tradeOrderUpdateService.updateOrderPrice(reqVO);
        return success(true);
    }


    /**
     * 修改订单收货地址
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-address")

    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateOrderAddress(@RequestBody TradeOrderUpdateAddressReqVO reqVO) {
        tradeOrderUpdateService.updateOrderAddress(reqVO);
        return success(true);
    }


    /**
     * 订单核销
     * 
     * @param id 交易订单编号
     * @return
     */
    @PutMapping("/pick-up-by-id")

    @PreAuthorize("@ss.hasPermission('trade:order:pick-up')")
    public CommonResult<Boolean> pickUpOrderById(@RequestParam("id") Long id) {
        tradeOrderUpdateService.pickUpOrderByAdmin(id);
        return success(true);
    }


    /**
     * 订单核销
     * 
     * @param pickUpVerifyCode 自提核销码
     * @return
     */
    @PutMapping("/pick-up-by-verify-code")

    @PreAuthorize("@ss.hasPermission('trade:order:pick-up')")
    public CommonResult<Boolean> pickUpOrderByVerifyCode(@RequestParam("pickUpVerifyCode") String pickUpVerifyCode) {
        tradeOrderUpdateService.pickUpOrderByAdmin(pickUpVerifyCode);
        return success(true);
    }


    /**
     * 查询核销码对应的订单
     * 
     * @param pickUpVerifyCode 自提核销码
     * @return
     */
    @GetMapping("/get-by-pick-up-verify-code")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<TradeOrderDetailRespVO> getByPickUpVerifyCode(
            @RequestParam("pickUpVerifyCode") String pickUpVerifyCode) {

        TradeOrderDO tradeOrder = tradeOrderUpdateService.getByPickUpVerifyCode(pickUpVerifyCode);
        return success(TradeOrderConvert.INSTANCE.convert2(tradeOrder, null));
    }

}
