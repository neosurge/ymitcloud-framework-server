package com.ymitcloud.module.statistics.dal.mysql.member;


import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberRegisterCountRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberSexStatisticsRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberTerminalStatisticsRespVO;

import com.ymitcloud.module.statistics.service.trade.bo.MemberAreaStatisticsRespBO;


/**
 * 会员信息的统计 Mapper
 *
 * @author owen
 */
@Mapper
@SuppressWarnings("rawtypes")
public interface MemberStatisticsMapper extends BaseMapperX {


    // TODO @云码：已经 review
    List<MemberAreaStatisticsRespBO> selectSummaryListByAreaId();

    // TODO @云码：已经 review
    List<MemberSexStatisticsRespVO> selectSummaryListBySex();

    // TODO @云码：已经 review
    List<MemberTerminalStatisticsRespVO> selectSummaryListByRegisterTerminal();

    // TODO @云码：已经 review
    Integer selectUserCount(@Param("beginTime") LocalDateTime beginTime,
                            @Param("endTime") LocalDateTime endTime);

    // TODO @云码：已经 review

    /**
     * 获得用户的每天注册数量列表
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 每天注册数量列表
     */
    List<MemberRegisterCountRespVO> selectListByCreateTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                                  @Param("endTime") LocalDateTime endTime);

}
