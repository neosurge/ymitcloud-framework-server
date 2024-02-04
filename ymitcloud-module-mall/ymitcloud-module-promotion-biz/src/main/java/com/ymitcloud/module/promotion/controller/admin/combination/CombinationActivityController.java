package com.ymitcloud.module.promotion.controller.admin.combination;


import static cn.hutool.core.collection.CollUtil.newArrayList;
import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.ymitcloud.module.promotion.controller.admin.combination.vo.activity.CombinationActivityCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.activity.CombinationActivityPageItemRespVO;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.activity.CombinationActivityPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.activity.CombinationActivityRespVO;
import com.ymitcloud.module.promotion.controller.admin.combination.vo.activity.CombinationActivityUpdateReqVO;

import com.ymitcloud.module.promotion.convert.combination.CombinationActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationProductDO;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import com.ymitcloud.module.promotion.enums.combination.CombinationRecordStatusEnum;
import com.ymitcloud.module.promotion.service.combination.CombinationActivityService;
import com.ymitcloud.module.promotion.service.combination.CombinationRecordService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 拼团活动
 */

@RestController
@RequestMapping("/promotion/combination-activity")
@Validated
public class CombinationActivityController {

    @Resource
    private CombinationActivityService combinationActivityService;
    @Resource
    private CombinationRecordService combinationRecordService;

    @Resource
    private ProductSpuApi productSpuApi;


    /**
     * 创建拼团活动
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:create')")
    public CommonResult<Long> createCombinationActivity(
            @Valid @RequestBody CombinationActivityCreateReqVO createReqVO) {
        return success(combinationActivityService.createCombinationActivity(createReqVO));
    }

    /**
     * 更新拼团活动
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:update')")
    public CommonResult<Boolean> updateCombinationActivity(
            @Valid @RequestBody CombinationActivityUpdateReqVO updateReqVO) {

        combinationActivityService.updateCombinationActivity(updateReqVO);
        return success(true);
    }


    /**
     * 关闭拼团活动
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/close")

    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:close')")
    public CommonResult<Boolean> closeSeckillActivity(@RequestParam("id") Long id) {
        combinationActivityService.closeCombinationActivityById(id);
        return success(true);
    }


    /**
     * 删除拼团活动
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:delete')")
    public CommonResult<Boolean> deleteCombinationActivity(@RequestParam("id") Long id) {
        combinationActivityService.deleteCombinationActivity(id);
        return success(true);
    }


    /**
     * 获得拼团活动
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:query')")
    public CommonResult<CombinationActivityRespVO> getCombinationActivity(@RequestParam("id") Long id) {
        CombinationActivityDO activity = combinationActivityService.getCombinationActivity(id);
        List<CombinationProductDO> products = combinationActivityService
                .getCombinationProductListByActivityIds(newArrayList(id));
        return success(CombinationActivityConvert.INSTANCE.convert(activity, products));
    }

    /**
     * 获得拼团活动分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:query')")
    public CommonResult<PageResult<CombinationActivityPageItemRespVO>> getCombinationActivityPage(
            @Valid CombinationActivityPageReqVO pageVO) {
        // 查询拼团活动
        PageResult<CombinationActivityDO> pageResult = combinationActivityService.getCombinationActivityPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 统计数据
        Set<Long> activityIds = convertSet(pageResult.getList(), CombinationActivityDO::getId);

        Map<Long, Integer> groupCountMap = combinationRecordService.getCombinationRecordCountMapByActivity(activityIds,
                null, CombinationRecordDO.HEAD_ID_GROUP);
        Map<Long, Integer> groupSuccessCountMap = combinationRecordService.getCombinationRecordCountMapByActivity(
                activityIds, CombinationRecordStatusEnum.SUCCESS.getStatus(), CombinationRecordDO.HEAD_ID_GROUP);
        Map<Long, Integer> recordCountMap = combinationRecordService.getCombinationRecordCountMapByActivity(activityIds,
                null, null);
        // 拼接数据
        List<CombinationProductDO> products = combinationActivityService
                .getCombinationProductListByActivityIds(convertSet(pageResult.getList(), CombinationActivityDO::getId));
        List<ProductSpuRespDTO> spus = productSpuApi
                .getSpuList(convertSet(pageResult.getList(), CombinationActivityDO::getSpuId));
        return success(CombinationActivityConvert.INSTANCE.convertPage(pageResult, products, groupCountMap,
                groupSuccessCountMap, recordCountMap, spus));

    }

}
