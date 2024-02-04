package com.ymitcloud.module.promotion.controller.app.combination;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.promotion.controller.app.combination.vo.record.AppCombinationRecordDetailRespVO;
import com.ymitcloud.module.promotion.controller.app.combination.vo.record.AppCombinationRecordRespVO;
import com.ymitcloud.module.promotion.controller.app.combination.vo.record.AppCombinationRecordSummaryRespVO;
import com.ymitcloud.module.promotion.convert.combination.CombinationActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import com.ymitcloud.module.promotion.enums.combination.CombinationRecordStatusEnum;
import com.ymitcloud.module.promotion.service.combination.CombinationRecordService;
import com.ymitcloud.module.trade.api.order.TradeOrderApi;


import jakarta.annotation.Resource;
import jakarta.validation.constraints.Max;

/**
 * 用户 APP - 拼团活动
 */

@RestController
@RequestMapping("/promotion/combination-record")
@Validated
public class AppCombinationRecordController {

    @Resource
    private CombinationRecordService combinationRecordService;
    @Resource
    @Lazy
    private TradeOrderApi tradeOrderApi;


    /**
     * 获得拼团记录的概要信息
     * 
     * @return
     */
    @GetMapping("/get-summary")

    public CommonResult<AppCombinationRecordSummaryRespVO> getCombinationRecordSummary() {
        AppCombinationRecordSummaryRespVO summary = new AppCombinationRecordSummaryRespVO();
        // 1. 获得拼团参与用户数量
        Long userCount = combinationRecordService.getCombinationUserCount();
        if (userCount == 0) {
            summary.setAvatars(Collections.emptyList());
            summary.setUserCount(userCount);
            return success(summary);
        }
        summary.setUserCount(userCount);

        // 2. 获得拼团记录头像

        List<CombinationRecordDO> records = combinationRecordService
                .getLatestCombinationRecordList(AppCombinationRecordSummaryRespVO.AVATAR_COUNT);

        summary.setAvatars(convertList(records, CombinationRecordDO::getAvatar));
        return success(summary);
    }


    /**
     * 获得最近 n 条拼团记录（团长发起的）
     * 
     * @param activityId 拼团活动编号
     * @param status     拼团状态
     * @param count      数量
     * @return
     */
    @GetMapping("/get-head-list")

    public CommonResult<List<AppCombinationRecordRespVO>> getHeadCombinationRecordList(
            @RequestParam(value = "activityId", required = false) Long activityId,
            @RequestParam("status") Integer status,
            @RequestParam(value = "count", defaultValue = "20") @Max(20) Integer count) {

        return success(CombinationActivityConvert.INSTANCE
                .convertList3(combinationRecordService.getHeadCombinationRecordList(activityId, status, count)));
    }

    /**
     * 获得拼团记录明细
     * 
     * @param id 拼团记录编号
     * @return
     */
    @GetMapping("/get-detail")

    public CommonResult<AppCombinationRecordDetailRespVO> getCombinationRecordDetail(@RequestParam("id") Long id) {
        // 1. 查找这条拼团记录
        CombinationRecordDO record = combinationRecordService.getCombinationRecordById(id);
        if (record == null) {
            return success(null);
        }

        // 2. 查找该拼团的参团记录
        CombinationRecordDO headRecord;
        List<CombinationRecordDO> memberRecords;
        if (Objects.equals(record.getHeadId(), CombinationRecordDO.HEAD_ID_GROUP)) { // 情况一：团长
            headRecord = record;
            memberRecords = combinationRecordService.getCombinationRecordListByHeadId(record.getId());
        } else { // 情况二：团员
            headRecord = combinationRecordService.getCombinationRecordById(record.getHeadId());
            memberRecords = combinationRecordService.getCombinationRecordListByHeadId(headRecord.getId());
        }

        // 3. 拼接数据
        return success(CombinationActivityConvert.INSTANCE.convert(getLoginUserId(), headRecord, memberRecords));
    }


    /**
     * 取消拼团
     * 
     * @param id 拼团记录编号
     * @return
     */
    @GetMapping("/cancel")

    public CommonResult<Boolean> cancelCombinationRecord(@RequestParam("id") Long id) {
        Long userId = getLoginUserId();
        // 1、查找这条拼团记录
        CombinationRecordDO record = combinationRecordService.getCombinationRecordByIdAndUser(userId, id);
        if (record == null) {
            return success(Boolean.FALSE);
        }
        // 1.1、需要先校验拼团记录未完成；
        if (!CombinationRecordStatusEnum.isInProgress(record.getStatus())) {
            return success(Boolean.FALSE);
        }

        // 2. 取消已支付的订单
        tradeOrderApi.cancelPaidOrder(userId, record.getOrderId());
        // 3. 取消拼团记录
        combinationRecordService.cancelCombinationRecord(userId, record.getId(), record.getHeadId());
        return success(Boolean.TRUE);
    }

}
