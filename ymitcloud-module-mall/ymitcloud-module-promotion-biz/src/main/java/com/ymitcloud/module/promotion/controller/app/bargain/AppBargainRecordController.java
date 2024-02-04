package com.ymitcloud.module.promotion.controller.app.bargain;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.product.api.spu.ProductSpuApi;
import com.ymitcloud.module.product.api.spu.dto.ProductSpuRespDTO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.record.AppBargainRecordCreateReqVO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.record.AppBargainRecordDetailRespVO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.record.AppBargainRecordRespVO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.record.AppBargainRecordSummaryRespVO;
import com.ymitcloud.module.promotion.convert.bargain.BargainRecordConvert;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainRecordDO;
import com.ymitcloud.module.promotion.enums.bargain.BargainRecordStatusEnum;
import com.ymitcloud.module.promotion.service.bargain.BargainActivityService;
import com.ymitcloud.module.promotion.service.bargain.BargainHelpService;
import com.ymitcloud.module.promotion.service.bargain.BargainRecordService;
import com.ymitcloud.module.trade.api.order.TradeOrderApi;
import com.ymitcloud.module.trade.api.order.dto.TradeOrderRespDTO;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import jakarta.annotation.Resource;

/**
 * 用户 App - 砍价记录
 */

@RestController
@RequestMapping("/promotion/bargain-record")
@Validated
public class AppBargainRecordController {

    @Resource
    private BargainHelpService bargainHelpService;
    @Resource
    private BargainRecordService bargainRecordService;
    @Resource
    private BargainActivityService bargainActivityService;

    @Resource
    private TradeOrderApi tradeOrderApi;
    @Resource
    private MemberUserApi memberUserApi;
    @Resource
    private ProductSpuApi productSpuApi;


    /**
     * 获得砍价记录的概要信息
     * 
     * @return
     */
    @GetMapping("/get-summary")
    public CommonResult<AppBargainRecordSummaryRespVO> getBargainRecordSummary() {
        // 砍价成功的用户数量
        Integer successUserCount = bargainRecordService
                .getBargainRecordUserCount(BargainRecordStatusEnum.SUCCESS.getStatus());
        if (successUserCount == 0) {
            return success(
                    new AppBargainRecordSummaryRespVO().setSuccessUserCount(0).setSuccessList(Collections.emptyList()));
        }
        // 砍价成功的用户列表
        List<BargainRecordDO> successList = bargainRecordService
                .getBargainRecordList(BargainRecordStatusEnum.SUCCESS.getStatus(), 7);
        List<BargainActivityDO> activityList = bargainActivityService
                .getBargainActivityList(convertSet(successList, BargainRecordDO::getActivityId));
        Map<Long, MemberUserRespDTO> userMap = memberUserApi
                .getUserMap(convertSet(successList, BargainRecordDO::getUserId));

        // 拼接返回
        return success(BargainRecordConvert.INSTANCE.convert(successUserCount, successList, activityList, userMap));
    }


    /**
     * 获得砍价记录的明细
     * 
     * @param id         砍价记录编号
     * @param activityId 砍价活动编号
     * @return
     */
    @GetMapping("/get-detail")

    public CommonResult<AppBargainRecordDetailRespVO> getBargainRecordDetail(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "activityId", required = false) Long activityId) {
        // 1. 查询砍价记录 + 砍价活动
        Assert.isTrue(id != null || activityId != null, "砍价记录编号和活动编号不能同时为空");
        BargainRecordDO record = id != null ? bargainRecordService.getBargainRecord(id)
                : bargainRecordService.getLastBargainRecord(getLoginUserId(), activityId);
        if (activityId == null || record != null) {
            activityId = record.getActivityId();
        }
        // 2. 查询助力记录
        Long userId = getLoginUserId();
        Integer helpAction = getHelpAction(userId, record, activityId);
        // 3. 如果是自己的订单，则查询订单信息

        TradeOrderRespDTO order = record != null && record.getOrderId() != null
                && record.getUserId().equals(getLoginUserId()) ? tradeOrderApi.getOrder(record.getOrderId()) : null;

        // TODO 继续查询别的字段

        // 拼接返回
        return success(BargainRecordConvert.INSTANCE.convert02(record, helpAction, order));
    }

    private Integer getHelpAction(Long userId, BargainRecordDO record, Long activityId) {
        // 0.1 如果没有活动，无法帮砍
        if (activityId == null) {
            return null;
        }
        // 0.2 如果是自己的砍价记录，无法帮砍
        if (record != null && record.getUserId().equals(userId)) {
            return null;
        }

        // 1. 判断是否已经助力

        if (record != null && bargainHelpService.getBargainHelp(record.getId(), userId) != null) {

            return AppBargainRecordDetailRespVO.HELP_ACTION_SUCCESS;
        }
        // 2. 判断是否满助力
        BargainActivityDO activity = bargainActivityService.getBargainActivity(activityId);
        if (activity != null

                && bargainHelpService.getBargainHelpCountByActivity(activityId, userId) >= activity.getBargainCount()) {

            return AppBargainRecordDetailRespVO.HELP_ACTION_FULL;
        }
        // 3. 允许助力
        return AppBargainRecordDetailRespVO.HELP_ACTION_NONE;
    }


    /**
     * 获得砍价记录的分页
     * 
     * @param pageParam
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppBargainRecordRespVO>> getBargainRecordPage(PageParam pageParam) {
        PageResult<BargainRecordDO> pageResult = bargainRecordService.getBargainRecordPage(getLoginUserId(), pageParam);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接数据

        List<BargainActivityDO> activityList = bargainActivityService
                .getBargainActivityList(convertSet(pageResult.getList(), BargainRecordDO::getActivityId));
        List<ProductSpuRespDTO> spuList = productSpuApi
                .getSpuList(convertSet(pageResult.getList(), BargainRecordDO::getSpuId));
        List<TradeOrderRespDTO> orderList = tradeOrderApi
                .getOrderList(convertSet(pageResult.getList(), BargainRecordDO::getOrderId));
        return success(BargainRecordConvert.INSTANCE.convertPage02(pageResult, activityList, spuList, orderList));
    }

    /**
     * 创建砍价记录
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthenticated
    public CommonResult<Long> createBargainRecord(@RequestBody AppBargainRecordCreateReqVO reqVO) {
        Long recordId = bargainRecordService.createBargainRecord(getLoginUserId(), reqVO);
        return success(recordId);
    }

}
