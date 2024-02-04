package com.ymitcloud.module.statistics.dal.mysql.trade;


import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeOrderSummaryRespVO;
import com.ymitcloud.module.statistics.controller.admin.trade.vo.TradeOrderTrendRespVO;
import com.ymitcloud.module.statistics.dal.dataobject.trade.TradeStatisticsDO;

import com.ymitcloud.module.statistics.service.trade.bo.MemberAreaStatisticsRespBO;


/**
 * 交易订单的统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface TradeOrderStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {


    // TODO 云码：已经 review
    List<MemberAreaStatisticsRespBO> selectSummaryListByAreaId();

    // TODO 云码：已经 review
    Integer selectCountByCreateTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                           @Param("endTime") LocalDateTime endTime);

    // TODO 云码：已经 review
    Integer selectCountByPayTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                        @Param("endTime") LocalDateTime endTime);

    // TODO 云码：已经 review
    Integer selectSummaryPriceByPayTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                               @Param("endTime") LocalDateTime endTime);

    // TODO 云码：已经 review
    Integer selectUserCountByCreateTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                               @Param("endTime") LocalDateTime endTime);

    // TODO 云码：已经 review
    Integer selectUserCountByPayTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                            @Param("endTime") LocalDateTime endTime);

    // TODO 云码：已经 review

    /**
     * 按照支付时间统计订单（按天分组）
     *
     * @param beginTime 支付起始时间
     * @param endTime   支付截止时间
     * @return 订单统计列表
     */
    List<TradeOrderTrendRespVO> selectListByPayTimeBetweenAndGroupByDay(@Param("beginTime") LocalDateTime beginTime,
                                                                        @Param("endTime") LocalDateTime endTime);


    // TODO 云码：已经 review

    /**
     * 按照支付时间统计订单（按月分组）
     *
     * @param beginTime 支付起始时间
     * @param endTime   支付截止时间
     * @return 订单统计列表
     */
    List<TradeOrderTrendRespVO> selectListByPayTimeBetweenAndGroupByMonth(@Param("beginTime") LocalDateTime beginTime,
                                                                          @Param("endTime") LocalDateTime endTime);


    // TODO @云码：已经 review
    Long selectCountByStatusAndDeliveryType(@Param("status") Integer status, @Param("deliveryType") Integer deliveryType);

    // TODO 云码：已经 review

    TradeOrderSummaryRespVO selectPaySummaryByStatusAndPayTimeBetween(@Param("status") Integer status,
                                                                      @Param("beginTime") LocalDateTime beginTime,
                                                                      @Param("endTime") LocalDateTime endTime);

}
