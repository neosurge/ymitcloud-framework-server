package com.ymitcloud.module.promotion.controller.app.activity;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertMultiMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.promotion.controller.app.activity.vo.AppActivityRespVO;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.seckill.SeckillActivityDO;
import com.ymitcloud.module.promotion.enums.common.PromotionTypeEnum;
import com.ymitcloud.module.promotion.service.bargain.BargainActivityService;
import com.ymitcloud.module.promotion.service.combination.CombinationActivityService;
import com.ymitcloud.module.promotion.service.seckill.SeckillActivityService;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import jakarta.annotation.Resource;

/**
 * 用户 APP - 营销活动
 */

@RestController
@RequestMapping("/promotion/activity")
@Validated
public class AppActivityController {

    @Resource
    private CombinationActivityService combinationActivityService;
    @Resource
    private SeckillActivityService seckillActivityService;
    @Resource
    private BargainActivityService bargainActivityService;


    /**
     * 获得单个商品，近期参与的每个活动
     * 
     * @param spuId 商品编号
     * @return
     */
    @GetMapping("/list-by-spu-id")

    public CommonResult<List<AppActivityRespVO>> getActivityListBySpuId(@RequestParam("spuId") Long spuId) {
        // 每种活动，只返回一个
        return success(getAppActivityList(Collections.singletonList(spuId)));
    }


    /**
     * 获得多个商品，近期参与的每个活动
     * 
     * @param spuIds 商品编号数组
     * @return
     */
    @GetMapping("/list-by-spu-ids")
    public CommonResult<Map<Long, List<AppActivityRespVO>>> getActivityListBySpuIds(
            @RequestParam("spuIds") List<Long> spuIds) {

        if (CollUtil.isEmpty(spuIds)) {
            return success(MapUtil.empty());
        }
        // 每种活动，只返回一个；key 为 SPU 编号
        return success(convertMultiMap(getAppActivityList(spuIds), AppActivityRespVO::getSpuId));
    }

    private List<AppActivityRespVO> getAppActivityList(Collection<Long> spuIds) {
        if (CollUtil.isEmpty(spuIds)) {
            return new ArrayList<>();
        }
        LocalDateTime now = LocalDateTime.now();
        List<AppActivityRespVO> activityList = new ArrayList<>();

        // 1. 拼团活动 - 获取开启的且开始的且没有结束的活动

        List<CombinationActivityDO> combinationActivities = combinationActivityService
                .getCombinationActivityBySpuIdsAndStatusAndDateTimeLt(spuIds, CommonStatusEnum.ENABLE.getStatus(), now);

        if (CollUtil.isNotEmpty(combinationActivities)) {
            combinationActivities.forEach(item -> {
                activityList.add(new AppActivityRespVO().setId(item.getId())
                        .setType(PromotionTypeEnum.COMBINATION_ACTIVITY.getType()).setName(item.getName())
                        .setSpuId(item.getSpuId()).setStartTime(item.getStartTime()).setEndTime(item.getEndTime()));
            });
        }

        // 2. 秒杀活动 - 获取开启的且开始的且没有结束的活动

        List<SeckillActivityDO> seckillActivities = seckillActivityService
                .getSeckillActivityBySpuIdsAndStatusAndDateTimeLt(spuIds, CommonStatusEnum.ENABLE.getStatus(), now);

        if (CollUtil.isNotEmpty(seckillActivities)) {
            seckillActivities.forEach(item -> {
                activityList.add(new AppActivityRespVO().setId(item.getId())
                        .setType(PromotionTypeEnum.SECKILL_ACTIVITY.getType()).setName(item.getName())
                        .setSpuId(item.getSpuId()).setStartTime(item.getStartTime()).setEndTime(item.getEndTime()));
            });
        }

        // 3. 砍价活动 - 获取开启的且开始的且没有结束的活动

        List<BargainActivityDO> bargainActivities = bargainActivityService
                .getBargainActivityBySpuIdsAndStatusAndDateTimeLt(spuIds, CommonStatusEnum.ENABLE.getStatus(), now);

        if (CollUtil.isNotEmpty(bargainActivities)) {
            bargainActivities.forEach(item -> {
                activityList.add(new AppActivityRespVO().setId(item.getId())
                        .setType(PromotionTypeEnum.BARGAIN_ACTIVITY.getType()).setName(item.getName())
                        .setSpuId(item.getSpuId()).setStartTime(item.getStartTime()).setEndTime(item.getEndTime()));
            });
        }
        return activityList;
    }

}
