package com.ymitcloud.module.statistics.dal.mysql.infra;

import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;


// TODO @云码：api 访问日志，现在会清理，可能要单独有个偏业务的访问表；

/**
 * API 访问日志的统计 Mapper
 *
 * @author owen
 */
@Mapper
@SuppressWarnings("rawtypes")
public interface ApiAccessLogStatisticsMapper extends BaseMapperX {


    // TODO 云码：已经 review

    Integer selectIpCountByUserTypeAndCreateTimeBetween(@Param("userType") Integer userType,
                                                        @Param("beginTime") LocalDateTime beginTime,
                                                        @Param("endTime") LocalDateTime endTime);


    // TODO 云码：已经 review

    Integer selectUserCountByUserTypeAndCreateTimeBetween(@Param("userType") Integer userType,
                                                          @Param("beginTime") LocalDateTime beginTime,
                                                          @Param("endTime") LocalDateTime endTime);

}
