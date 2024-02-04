package com.ymitcloud.module.promotion.api.reward;

import com.ymitcloud.module.promotion.api.reward.dto.RewardActivityMatchRespDTO;
import com.ymitcloud.module.promotion.service.reward.RewardActivityService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 满减送活动 API 实现类
 *

 * @author 

 */
@Service
@Validated
public class RewardActivityApiImpl implements RewardActivityApi {

    @Resource
    private RewardActivityService rewardActivityService;

    @Override
    public List<RewardActivityMatchRespDTO> getMatchRewardActivityList(Collection<Long> spuIds) {
        return rewardActivityService.getMatchRewardActivityList(spuIds);
    }

}
