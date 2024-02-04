package com.ymitcloud.module.statistics.dal.mysql.trade;

import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import com.ymitcloud.module.statistics.service.trade.bo.AfterSaleSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 售后订单的统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface AfterSaleStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {


    // TODO 云码：已 review
    AfterSaleSummaryRespBO selectSummaryByRefundTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                            @Param("endTime") LocalDateTime endTime);

    // TODO 云码：已经 review

    Long selectCountByStatus(@Param("status") Integer status);

}
