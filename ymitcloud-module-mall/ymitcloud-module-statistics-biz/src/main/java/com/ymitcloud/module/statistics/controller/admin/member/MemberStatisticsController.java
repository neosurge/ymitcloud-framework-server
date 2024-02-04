package com.ymitcloud.module.statistics.controller.admin.member;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.UserTypeEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.statistics.controller.admin.common.vo.DataComparisonRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberAnalyseDataRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberAnalyseReqVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberAnalyseRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberAreaStatisticsRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberCountRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberRegisterCountRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberSexStatisticsRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberSummaryRespVO;
import com.ymitcloud.module.statistics.controller.admin.member.vo.MemberTerminalStatisticsRespVO;
import com.ymitcloud.module.statistics.convert.member.MemberStatisticsConvert;
import com.ymitcloud.module.statistics.service.infra.ApiAccessLogStatisticsService;
import com.ymitcloud.module.statistics.service.member.MemberStatisticsService;
import com.ymitcloud.module.statistics.service.trade.TradeOrderStatisticsService;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理后台 - 会员统计
 */

@RestController
@RequestMapping("/statistics/member")
@Validated
@Slf4j
public class MemberStatisticsController {

    @Resource
    private MemberStatisticsService memberStatisticsService;
    @Resource
    private TradeOrderStatisticsService tradeOrderStatisticsService;
    @Resource
    private ApiAccessLogStatisticsService apiAccessLogStatisticsService;


    /**
     * 获得会员统计（实时统计）
     * 
     * @return
     */
    @GetMapping("/summary")

    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<MemberSummaryRespVO> getMemberSummary() {
        return success(memberStatisticsService.getMemberSummary());
    }


    /**
     * 获得会员分析数据
     * 
     * @param reqVO
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/analyse")

    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<MemberAnalyseRespVO> getMemberAnalyse(MemberAnalyseReqVO reqVO) {
        // 1. 查询数据
        LocalDateTime beginTime = ArrayUtil.get(reqVO.getTimes(), 0);
        LocalDateTime endTime = ArrayUtil.get(reqVO.getTimes(), 1);
        // 1.1 查询分析对照数据

        DataComparisonRespVO<MemberAnalyseDataRespVO> comparisonData = memberStatisticsService
                .getMemberAnalyseComparisonData(beginTime, endTime);
        // TODO @疯狂：这个可能有点特殊，要按照 create_time 来查询；不然它的漏斗就不统一；因为是访问数量 > 今日下单人 >
        // 今日支付人；是一个统一的维度；

        // 1.2 查询成交用户数量
        Integer payUserCount = tradeOrderStatisticsService.getPayUserCount(beginTime, endTime);
        // 1.3 计算客单价
        int atv = 0;
        if (payUserCount != null && payUserCount > 0) {
            // TODO @疯狂：类似上面的 payUserCount
            Integer payPrice = tradeOrderStatisticsService.getOrderPayPrice(beginTime, endTime);
            atv = NumberUtil.div(payPrice, payUserCount).intValue();
        }
        // 1.4 查询访客数量

        Integer visitUserCount = apiAccessLogStatisticsService.getIpCount(UserTypeEnum.MEMBER.getValue(), beginTime,
                endTime);

        // 1.5 下单用户数量
        Integer orderUserCount = tradeOrderStatisticsService.getOrderUserCount(beginTime, endTime);

        // 2. 拼接返回

        return success(MemberStatisticsConvert.INSTANCE.convert(visitUserCount, orderUserCount, payUserCount, atv,
                comparisonData));
    }

    /**
     * 按照省份，获得会员统计列表
     * 
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/area-statistics-list")

    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<List<MemberAreaStatisticsRespVO>> getMemberAreaStatisticsList() {
        return success(memberStatisticsService.getMemberAreaStatisticsList());
    }


    /**
     * 按照性别，获得会员统计列表
     * 
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/sex-statistics-list")

    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<List<MemberSexStatisticsRespVO>> getMemberSexStatisticsList() {
        return success(memberStatisticsService.getMemberSexStatisticsList());
    }


    /**
     * 按照终端，获得会员统计列表
     * 
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/terminal-statistics-list")

    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<List<MemberTerminalStatisticsRespVO>> getMemberTerminalStatisticsList() {
        return success(memberStatisticsService.getMemberTerminalStatisticsList());
    }


    /**
     * 获得用户数量对照
     * 
     * @return
     */
    // TODO 云码：已经 review
    // TODO @疯狂：要注意 date 的排序；
    @GetMapping("/user-count-comparison")

    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<DataComparisonRespVO<MemberCountRespVO>> getUserCountComparison() {
        return success(memberStatisticsService.getUserCountComparison());
    }


    /**
     * 获得会员注册数量列表
     * 
     * @param reqVO
     * @return
     */
    // TODO 云码：已经 review
    @GetMapping("/register-count-list")
    @PreAuthorize("@ss.hasPermission('statistics:member:query')")
    public CommonResult<List<MemberRegisterCountRespVO>> getMemberRegisterCountList(MemberAnalyseReqVO reqVO) {
        return success(memberStatisticsService.getMemberRegisterCountList(ArrayUtil.get(reqVO.getTimes(), 0),
                ArrayUtil.get(reqVO.getTimes(), 1)));

    }

}
