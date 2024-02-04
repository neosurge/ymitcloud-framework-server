package com.ymitcloud.module.trade.controller.app.brokerage;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;

import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserBindReqVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserChildSummaryPageReqVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserChildSummaryRespVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserMySummaryRespVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserRankByPriceRespVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserRankByUserCountRespVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserRankPageReqVO;
import com.ymitcloud.module.trade.controller.app.brokerage.vo.user.AppBrokerageUserRespVO;

import com.ymitcloud.module.trade.convert.brokerage.BrokerageRecordConvert;
import com.ymitcloud.module.trade.convert.brokerage.BrokerageUserConvert;
import com.ymitcloud.module.trade.dal.dataobject.brokerage.BrokerageUserDO;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageRecordBizTypeEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageRecordStatusEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageWithdrawStatusEnum;
import com.ymitcloud.module.trade.service.brokerage.BrokerageRecordService;
import com.ymitcloud.module.trade.service.brokerage.BrokerageUserService;
import com.ymitcloud.module.trade.service.brokerage.BrokerageWithdrawService;
import com.ymitcloud.module.trade.service.brokerage.bo.BrokerageWithdrawSummaryRespBO;


import cn.hutool.core.date.LocalDateTimeUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 APP - 分销用户
 */

@RestController
@RequestMapping("/trade/brokerage-user")
@Validated
@Slf4j
public class AppBrokerageUserController {

    @Resource
    private BrokerageUserService brokerageUserService;
    @Resource
    private BrokerageRecordService brokerageRecordService;
    @Resource
    private BrokerageWithdrawService brokerageWithdrawService;

    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 获得个人分销信息
     * 
     * @return
     */
    @GetMapping("/get")

    @PreAuthenticated
    public CommonResult<AppBrokerageUserRespVO> getBrokerageUser() {
        Optional<BrokerageUserDO> user = Optional.ofNullable(brokerageUserService.getBrokerageUser(getLoginUserId()));
        // 返回数据
        AppBrokerageUserRespVO respVO = new AppBrokerageUserRespVO()
                .setBrokerageEnabled(user.map(BrokerageUserDO::getBrokerageEnabled).orElse(false))
                .setBrokeragePrice(user.map(BrokerageUserDO::getBrokeragePrice).orElse(0))
                .setFrozenPrice(user.map(BrokerageUserDO::getFrozenPrice).orElse(0));
        return success(respVO);
    }


    /**
     * 绑定推广员
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/bind")

    @PreAuthenticated
    public CommonResult<Boolean> bindBrokerageUser(@Valid @RequestBody AppBrokerageUserBindReqVO reqVO) {
        return success(brokerageUserService.bindBrokerageUser(getLoginUserId(), reqVO.getBindUserId()));
    }


    /**
     * 获得个人分销统计
     * 
     * @return
     */
    @GetMapping("/get-summary")

    @PreAuthenticated
    public CommonResult<AppBrokerageUserMySummaryRespVO> getBrokerageUserSummary() {
        // 查询当前登录用户信息
        BrokerageUserDO brokerageUser = brokerageUserService.getBrokerageUser(getLoginUserId());
        // 统计用户昨日的佣金
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime beginTime = LocalDateTimeUtil.beginOfDay(yesterday);
        LocalDateTime endTime = LocalDateTimeUtil.endOfDay(yesterday);
        Integer yesterdayPrice = brokerageRecordService.getSummaryPriceByUserId(brokerageUser.getId(),
                BrokerageRecordBizTypeEnum.ORDER, BrokerageRecordStatusEnum.SETTLEMENT, beginTime, endTime);
        // 统计用户提现的佣金

        Integer withdrawPrice = brokerageWithdrawService
                .getWithdrawSummaryListByUserId(Collections.singleton(brokerageUser.getId()),
                        BrokerageWithdrawStatusEnum.AUDIT_SUCCESS)
                .stream().findFirst().map(BrokerageWithdrawSummaryRespBO::getPrice).orElse(0);
        // 统计分销用户数量（一级）
        Long firstBrokerageUserCount = brokerageUserService.getBrokerageUserCountByBindUserId(brokerageUser.getId(), 1);
        // 统计分销用户数量（二级）
        Long secondBrokerageUserCount = brokerageUserService.getBrokerageUserCountByBindUserId(brokerageUser.getId(),
                2);

        // 拼接返回
        return success(BrokerageUserConvert.INSTANCE.convert(yesterdayPrice, withdrawPrice, firstBrokerageUserCount,
                secondBrokerageUserCount, brokerageUser));
    }

    /**
     * 获得分销用户排行分页（基于用户量）
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/rank-page-by-user-count")
    @PreAuthenticated
    public CommonResult<PageResult<AppBrokerageUserRankByUserCountRespVO>> getBrokerageUserRankPageByUserCount(
            AppBrokerageUserRankPageReqVO pageReqVO) {
        // 分页查询
        PageResult<AppBrokerageUserRankByUserCountRespVO> pageResult = brokerageUserService
                .getBrokerageUserRankPageByUserCount(pageReqVO);
        // 拼接数据
        Map<Long, MemberUserRespDTO> userMap = memberUserApi
                .getUserMap(convertSet(pageResult.getList(), AppBrokerageUserRankByUserCountRespVO::getId));
        return success(BrokerageUserConvert.INSTANCE.convertPage03(pageResult, userMap));
    }

    /**
     * 获得分销用户排行分页（基于佣金）
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/rank-page-by-price")
    @PreAuthenticated
    public CommonResult<PageResult<AppBrokerageUserRankByPriceRespVO>> getBrokerageUserChildSummaryPageByPrice(
            AppBrokerageUserRankPageReqVO pageReqVO) {
        // 分页查询
        PageResult<AppBrokerageUserRankByPriceRespVO> pageResult = brokerageRecordService
                .getBrokerageUserChildSummaryPageByPrice(pageReqVO);
        // 拼接数据
        Map<Long, MemberUserRespDTO> userMap = memberUserApi
                .getUserMap(convertSet(pageResult.getList(), AppBrokerageUserRankByPriceRespVO::getId));
        return success(BrokerageRecordConvert.INSTANCE.convertPage03(pageResult, userMap));
    }

    /**
     * 获得下级分销统计分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/child-summary-page")
    @PreAuthenticated
    public CommonResult<PageResult<AppBrokerageUserChildSummaryRespVO>> getBrokerageUserChildSummaryPage(
            AppBrokerageUserChildSummaryPageReqVO pageReqVO) {
        PageResult<AppBrokerageUserChildSummaryRespVO> pageResult = brokerageUserService
                .getBrokerageUserChildSummaryPage(pageReqVO, getLoginUserId());
        return success(pageResult);
    }

    /**
     * 获得分销用户排行（基于佣金）
     * 
     * @param times 时间段
     * @return
     */
    @GetMapping("/get-rank-by-price")

    public CommonResult<Integer> getRankByPrice(
            @RequestParam("times") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND) LocalDateTime[] times) {
        return success(brokerageRecordService.getUserRankByPrice(getLoginUserId(), times));
    }

}
