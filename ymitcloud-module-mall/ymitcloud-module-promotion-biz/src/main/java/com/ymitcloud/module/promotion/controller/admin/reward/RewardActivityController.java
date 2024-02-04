package com.ymitcloud.module.promotion.controller.admin.reward;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.promotion.controller.admin.reward.vo.RewardActivityCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.reward.vo.RewardActivityPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.reward.vo.RewardActivityRespVO;
import com.ymitcloud.module.promotion.controller.admin.reward.vo.RewardActivityUpdateReqVO;
import com.ymitcloud.module.promotion.convert.reward.RewardActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.reward.RewardActivityDO;
import com.ymitcloud.module.promotion.service.reward.RewardActivityService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 满减送活动
 */

@RestController
@RequestMapping("/promotion/reward-activity")
@Validated
public class RewardActivityController {

    @Resource
    private RewardActivityService rewardActivityService;


    /**
     * 创建满减送活动
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:create')")
    public CommonResult<Long> createRewardActivity(@Valid @RequestBody RewardActivityCreateReqVO createReqVO) {
        return success(rewardActivityService.createRewardActivity(createReqVO));
    }


    /**
     * 更新满减送活动
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:update')")
    public CommonResult<Boolean> updateRewardActivity(@Valid @RequestBody RewardActivityUpdateReqVO updateReqVO) {
        rewardActivityService.updateRewardActivity(updateReqVO);
        return success(true);
    }


    /**
     * 关闭满减送活动
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/close")

    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:close')")
    public CommonResult<Boolean> closeRewardActivity(@RequestParam("id") Long id) {
        rewardActivityService.closeRewardActivity(id);
        return success(true);
    }


    /**
     * 删除满减送活动
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:delete')")
    public CommonResult<Boolean> deleteRewardActivity(@RequestParam("id") Long id) {
        rewardActivityService.deleteRewardActivity(id);
        return success(true);
    }


    /**
     * 获得满减送活动
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:query')")
    public CommonResult<RewardActivityRespVO> getRewardActivity(@RequestParam("id") Long id) {
        RewardActivityDO rewardActivity = rewardActivityService.getRewardActivity(id);
        return success(RewardActivityConvert.INSTANCE.convert(rewardActivity));
    }


    /**
     * 获得满减送活动分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:reward-activity:query')")
    public CommonResult<PageResult<RewardActivityRespVO>> getRewardActivityPage(@Valid RewardActivityPageReqVO pageVO) {
        PageResult<RewardActivityDO> pageResult = rewardActivityService.getRewardActivityPage(pageVO);
        return success(RewardActivityConvert.INSTANCE.convertPage(pageResult));
    }

}
