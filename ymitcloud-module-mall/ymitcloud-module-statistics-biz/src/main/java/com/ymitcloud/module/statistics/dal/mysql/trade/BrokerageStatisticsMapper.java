package com.ymitcloud.module.statistics.dal.mysql.trade;

import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 订单分销的统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface BrokerageStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {


    // TODO 云码：已经 review

    Integer selectSummaryPriceByStatusAndUnfreezeTimeBetween(@Param("bizType") Integer bizType,
                                                             @Param("status") Integer status,
                                                             @Param("beginTime") LocalDateTime beginTime,
                                                             @Param("endTime") LocalDateTime endTime);


    // TODO 云码：已经 review

    Long selectWithdrawCountByStatus(@Param("status") Integer status);

}
