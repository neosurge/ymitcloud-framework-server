package com.ymitcloud.module.trade.controller.admin.aftersale;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.pay.api.notify.dto.PayRefundNotifyReqDTO;

import com.ymitcloud.module.trade.controller.admin.aftersale.vo.AfterSaleDetailRespVO;
import com.ymitcloud.module.trade.controller.admin.aftersale.vo.AfterSaleDisagreeReqVO;
import com.ymitcloud.module.trade.controller.admin.aftersale.vo.AfterSalePageReqVO;
import com.ymitcloud.module.trade.controller.admin.aftersale.vo.AfterSaleRefuseReqVO;
import com.ymitcloud.module.trade.controller.admin.aftersale.vo.AfterSaleRespPageItemVO;

import com.ymitcloud.module.trade.convert.aftersale.AfterSaleConvert;
import com.ymitcloud.module.trade.dal.dataobject.aftersale.AfterSaleDO;
import com.ymitcloud.module.trade.dal.dataobject.aftersale.AfterSaleLogDO;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderDO;
import com.ymitcloud.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.ymitcloud.module.trade.service.aftersale.AfterSaleLogService;
import com.ymitcloud.module.trade.service.aftersale.AfterSaleService;
import com.ymitcloud.module.trade.service.order.TradeOrderQueryService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 售后订单
 */

@RestController
@RequestMapping("/trade/after-sale")
@Validated
@Slf4j
public class AfterSaleController {

    @Resource
    private AfterSaleService afterSaleService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;
    @Resource
    private AfterSaleLogService afterSaleLogService;
    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 获得售后订单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:query')")
    public CommonResult<PageResult<AfterSaleRespPageItemVO>> getAfterSalePage(@Valid AfterSalePageReqVO pageVO) {
        // 查询售后
        PageResult<AfterSaleDO> pageResult = afterSaleService.getAfterSalePage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 查询会员

        Map<Long, MemberUserRespDTO> memberUsers = memberUserApi
                .getUserMap(convertSet(pageResult.getList(), AfterSaleDO::getUserId));
        return success(AfterSaleConvert.INSTANCE.convertPage(pageResult, memberUsers));
    }

    /**
     * 获得售后订单详情
     * 
     * @param id 售后编号
     * @return
     */
    @GetMapping("/get-detail")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:query')")
    public CommonResult<AfterSaleDetailRespVO> getOrderDetail(@RequestParam("id") Long id) {
        // 查询订单
        AfterSaleDO afterSale = afterSaleService.getAfterSale(id);
        if (afterSale == null) {
            return success(null);
        }

        // 查询订单
        TradeOrderDO order = tradeOrderQueryService.getOrder(afterSale.getOrderId());
        // 查询订单项
        TradeOrderItemDO orderItem = tradeOrderQueryService.getOrderItem(afterSale.getOrderItemId());
        // 拼接数据
        MemberUserRespDTO user = memberUserApi.getUser(afterSale.getUserId());
        List<AfterSaleLogDO> logs = afterSaleLogService.getAfterSaleLogList(afterSale.getId());
        return success(AfterSaleConvert.INSTANCE.convert(afterSale, order, orderItem, user, logs));
    }


    /**
     * 同意售后
     * 
     * @param id 售后编号
     * @return
     */
    @PutMapping("/agree")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:agree')")
    public CommonResult<Boolean> agreeAfterSale(@RequestParam("id") Long id) {
        afterSaleService.agreeAfterSale(getLoginUserId(), id);
        return success(true);
    }


    /**
     * 拒绝售后
     * 
     * @param confirmReqVO
     * @return
     */
    @PutMapping("/disagree")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:disagree')")
    public CommonResult<Boolean> disagreeAfterSale(@RequestBody AfterSaleDisagreeReqVO confirmReqVO) {
        afterSaleService.disagreeAfterSale(getLoginUserId(), confirmReqVO);
        return success(true);
    }


    /**
     * 确认收货
     * 
     * @param id 售后编号
     * @return
     */
    @PutMapping("/receive")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:receive')")
    public CommonResult<Boolean> receiveAfterSale(@RequestParam("id") Long id) {
        afterSaleService.receiveAfterSale(getLoginUserId(), id);
        return success(true);
    }


    /**
     * 拒绝收货
     * 
     * @param refuseReqVO 售后编号
     * @return
     */
    @PutMapping("/refuse")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:receive')")
    public CommonResult<Boolean> refuseAfterSale(AfterSaleRefuseReqVO refuseReqVO) {
        afterSaleService.refuseAfterSale(getLoginUserId(), refuseReqVO);
        return success(true);
    }


    /**
     * 确认退款
     * 
     * @param id 售后编号
     * @return
     */
    @PutMapping("/refund")

    @PreAuthorize("@ss.hasPermission('trade:after-sale:refund')")
    public CommonResult<Boolean> refundAfterSale(@RequestParam("id") Long id) {
        afterSaleService.refundAfterSale(getLoginUserId(), getClientIP(), id);
        return success(true);
    }


    /**
     * 更新售后订单为已退款
     * 
     * @param notifyReqDTO
     * @return
     */
    @PostMapping("/update-refunded")

    @PermitAll // 无需登录，安全由 PayDemoOrderService 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateAfterRefund(@RequestBody PayRefundNotifyReqDTO notifyReqDTO) {
        // 目前业务逻辑，不需要做任何事情
        // 当然，退款会有小概率会失败的情况，可以监控失败状态，进行告警
        log.info("[updateAfterRefund][notifyReqDTO({})]", notifyReqDTO);
        return success(true);
    }

}
