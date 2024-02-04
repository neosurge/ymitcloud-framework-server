package com.ymitcloud.module.promotion.controller.admin.bargain;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import com.ymitcloud.module.product.api.spu.ProductSpuApi;
import com.ymitcloud.module.product.api.spu.dto.ProductSpuRespDTO;

import com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity.BargainActivityCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity.BargainActivityPageItemRespVO;
import com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity.BargainActivityPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity.BargainActivityRespVO;
import com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity.BargainActivityUpdateReqVO;

import com.ymitcloud.module.promotion.convert.bargain.BargainActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import com.ymitcloud.module.promotion.enums.bargain.BargainRecordStatusEnum;
import com.ymitcloud.module.promotion.service.bargain.BargainActivityService;
import com.ymitcloud.module.promotion.service.bargain.BargainHelpService;
import com.ymitcloud.module.promotion.service.bargain.BargainRecordService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 砍价活动
 */

@RestController
@RequestMapping("/promotion/bargain-activity")
@Validated
public class BargainActivityController {

    @Resource
    private BargainActivityService bargainActivityService;
    @Resource
    private BargainRecordService bargainRecordService;
    @Resource
    private BargainHelpService bargainHelpService;

    @Resource
    private ProductSpuApi spuApi;


    /**
     * 创建砍价活动
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-activity:create')")
    public CommonResult<Long> createBargainActivity(@Valid @RequestBody BargainActivityCreateReqVO createReqVO) {
        return success(bargainActivityService.createBargainActivity(createReqVO));
    }


    /**
     * 更新砍价活动
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-activity:update')")
    public CommonResult<Boolean> updateBargainActivity(@Valid @RequestBody BargainActivityUpdateReqVO updateReqVO) {
        bargainActivityService.updateBargainActivity(updateReqVO);
        return success(true);
    }


    /**
     * 关闭砍价活动
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/close")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-activity:close')")
    public CommonResult<Boolean> closeSeckillActivity(@RequestParam("id") Long id) {
        bargainActivityService.closeBargainActivityById(id);
        return success(true);
    }


    /**
     * 删除砍价活动
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-activity:delete')")
    public CommonResult<Boolean> deleteBargainActivity(@RequestParam("id") Long id) {
        bargainActivityService.deleteBargainActivity(id);
        return success(true);
    }


    /**
     * 获得砍价活动
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-activity:query')")
    public CommonResult<BargainActivityRespVO> getBargainActivity(@RequestParam("id") Long id) {
        return success(BargainActivityConvert.INSTANCE.convert(bargainActivityService.getBargainActivity(id)));
    }


    /**
     * 获得砍价活动分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:bargain-activity:query')")
    public CommonResult<PageResult<BargainActivityPageItemRespVO>> getBargainActivityPage(
            @Valid BargainActivityPageReqVO pageVO) {
        // 查询砍价活动
        PageResult<BargainActivityDO> pageResult = bargainActivityService.getBargainActivityPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接数据

        List<ProductSpuRespDTO> spuList = spuApi
                .getSpuList(convertList(pageResult.getList(), BargainActivityDO::getSpuId));

        // 统计数据
        Collection<Long> activityIds = convertList(pageResult.getList(), BargainActivityDO::getId);
        Map<Long, Integer> recordUserCountMap = bargainRecordService.getBargainRecordUserCountMap(activityIds, null);
        Map<Long, Integer> recordSuccessUserCountMap = bargainRecordService.getBargainRecordUserCountMap(activityIds,
                BargainRecordStatusEnum.SUCCESS.getStatus());
        Map<Long, Integer> helpUserCountMap = bargainHelpService.getBargainHelpUserCountMapByActivity(activityIds);

        return success(BargainActivityConvert.INSTANCE.convertPage(pageResult, spuList, recordUserCountMap,
                recordSuccessUserCountMap, helpUserCountMap));

    }

}
