package com.ymitcloud.module.statistics.controller.admin.trade;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.excel.core.util.ExcelUtils;
import com.ymitcloud.module.statistics.controller.admin.common.vo.DataComparisonRespVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeOrderCountRespVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeOrderSummaryRespVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeOrderTrendReqVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeOrderTrendRespVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeSummaryRespVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeTrendReqVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeTrendSummaryExcelVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeTrendSummaryRespVO;

import com.ymitcloud.module.statistics.convert.trade.TradeStatisticsConvert;
import com.ymitcloud.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import com.ymitcloud.module.statistics.service.trade.AfterSaleStatisticsService;
import com.ymitcloud.module.statistics.service.trade.BrokerageStatisticsService;
import com.ymitcloud.module.statistics.service.trade.TradeOrderStatisticsService;
import com.ymitcloud.module.statistics.service.trade.TradeStatisticsService;
import com.ymitcloud.module.statistics.service.trade.bo.TradeSummaryRespBO;


import com.ymitcloud.module.trade.enums.aftersale.AfterSaleStatusEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.ymitcloud.module.trade.enums.delivery.DeliveryTypeEnum;
import com.ymitcloud.module.trade.enums.order.TradeOrderStatusEnum;


import cn.hutool.core.util.ArrayUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
/**
 * 管理后台 - 交易统计
 */

@RestController
@RequestMapping("/statistics/trade")
@Validated
@Slf4j
public class TradeStatisticsController {

    @Resource
    private TradeStatisticsService tradeStatisticsService;
    @Resource
    private TradeOrderStatisticsService tradeOrderStatisticsService;
    @Resource
    private AfterSaleStatisticsService afterSaleStatisticsService;
    @Resource
    private BrokerageStatisticsService brokerageStatisticsService;


    /**
     * 获得交易统计
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/summary")

    @PreAuthorize("@ss.hasPermission('statistics:trade:query')")
    public CommonResult<DataComparisonRespVO<TradeSummaryRespVO>> getTradeSummaryComparison() {
        // 1.1 昨天的数据
        TradeSummaryRespBO yesterdayData = tradeStatisticsService.getTradeSummaryByDays(-1);
        // 1.2 前天的数据（用于对照昨天的数据）
        TradeSummaryRespBO beforeYesterdayData = tradeStatisticsService.getTradeSummaryByDays(-2);

        // 2.1 本月数据
        TradeSummaryRespBO monthData = tradeStatisticsService.getTradeSummaryByMonths(0);
        // 2.2 上月数据（用于对照本月的数据）
        TradeSummaryRespBO lastMonthData = tradeStatisticsService.getTradeSummaryByMonths(-1);
        // 拼接数据
        return success(TradeStatisticsConvert.INSTANCE.convert(yesterdayData, beforeYesterdayData, monthData, lastMonthData));
    }


    /**
     * 获得交易状况统计
     * @param reqVO
     * @return
     */
    // TODO @疯狂：【晚点再改和讨论；等首页的接口出来】这个要不还是叫 analyse，对比选中的时间段，和上一个时间段；类似 MemberStatisticsController 的 getMemberAnalyse
    @GetMapping("/trend/summary")

    @PreAuthorize("@ss.hasPermission('statistics:trade:query')")
    public CommonResult<DataComparisonRespVO<TradeTrendSummaryRespVO>> getTradeTrendSummaryComparison(
            TradeTrendReqVO reqVO) {
        return success(tradeStatisticsService.getTradeTrendSummaryComparison(ArrayUtil.get(reqVO.getTimes(), 0),
                ArrayUtil.get(reqVO.getTimes(), 1)));
    }


    /**
     * 获得交易状况明细
     * @param reqVO
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('statistics:trade:query')")
    public CommonResult<List<TradeTrendSummaryRespVO>> getTradeStatisticsList(TradeTrendReqVO reqVO) {
        List<TradeStatisticsDO> list = tradeStatisticsService.getTradeStatisticsList(ArrayUtil.get(reqVO.getTimes(), 0),
                ArrayUtil.get(reqVO.getTimes(), 1));
        return success(TradeStatisticsConvert.INSTANCE.convertList(list));
    }


    /**
     * 导出获得交易状况明细 Excel
     * @param reqVO
     * @param response
     * @throws IOException
     */
    // TODO 云码：已经 review
    @GetMapping("/export-excel")

    @PreAuthorize("@ss.hasPermission('statistics:trade:export')")
    public void exportTradeStatisticsExcel(TradeTrendReqVO reqVO, HttpServletResponse response) throws IOException {
        List<TradeStatisticsDO> list = tradeStatisticsService.getTradeStatisticsList(ArrayUtil.get(reqVO.getTimes(), 0),
                ArrayUtil.get(reqVO.getTimes(), 1));
        // 导出 Excel
        List<TradeTrendSummaryRespVO> voList = TradeStatisticsConvert.INSTANCE.convertList(list);
        List<TradeTrendSummaryExcelVO> data = TradeStatisticsConvert.INSTANCE.convertList02(voList);
        ExcelUtils.write(response, "交易状况.xls", "数据", TradeTrendSummaryExcelVO.class, data);
    }


    /**
     * 获得交易订单数量
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/order-count")

    @PreAuthorize("@ss.hasPermission('statistics:trade:query')")
    public CommonResult<TradeOrderCountRespVO> getOrderCount() {
        // 订单统计
        Long undeliveredCount = tradeOrderStatisticsService.getCountByStatusAndDeliveryType(
                TradeOrderStatusEnum.UNDELIVERED.getStatus(), DeliveryTypeEnum.EXPRESS.getType());
        // TODO @疯狂：订单支付后，如果是门店自提的，需要 update 成 DELIVERED；；目前还没搞~~突然反应过来
        Long pickUpCount = tradeOrderStatisticsService.getCountByStatusAndDeliveryType(
                TradeOrderStatusEnum.DELIVERED.getStatus(), DeliveryTypeEnum.PICK_UP.getType());
        // 售后统计
        Long afterSaleApplyCount = afterSaleStatisticsService.getCountByStatus(AfterSaleStatusEnum.APPLY);
        Long auditingWithdrawCount = brokerageStatisticsService.getWithdrawCountByStatus(BrokerageWithdrawStatusEnum.AUDITING);
        // 拼接返回
        return success(TradeStatisticsConvert.INSTANCE.convert(undeliveredCount, pickUpCount, afterSaleApplyCount, auditingWithdrawCount));
    }


    /**
     * 获得交易订单数量
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/order-comparison")

    @PreAuthorize("@ss.hasPermission('statistics:trade:query')")
    public CommonResult<DataComparisonRespVO<TradeOrderSummaryRespVO>> getOrderComparison() {
        return success(tradeOrderStatisticsService.getOrderComparison());
    }

/**
 * 获得订单量趋势统计
 * @param reqVO
 * @return
 */
    // TODO 云码：已经 review
    @GetMapping("/order-count-trend")

    @PreAuthorize("@ss.hasPermission('statistics:trade:query')")
    public CommonResult<List<DataComparisonRespVO<TradeOrderTrendRespVO>>> getOrderCountTrendComparison(@Valid TradeOrderTrendReqVO reqVO) {
        // TODO @疯狂：要注意 date 的排序；
        return success(tradeOrderStatisticsService.getOrderCountTrendComparison(reqVO));
    }

}
