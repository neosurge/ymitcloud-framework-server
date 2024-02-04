package com.ymitcloud.module.promotion.controller.app.combination;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.cache.CacheUtils.buildAsyncReloadingCache;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.api.spu.ProductSpuApi;
import com.ymitcloud.module.product.api.spu.dto.ProductSpuRespDTO;
import com.ymitcloud.module.promotion.controller.app.combination.vo.activity.AppCombinationActivityDetailRespVO;
import com.ymitcloud.module.promotion.controller.app.combination.vo.activity.AppCombinationActivityRespVO;
import com.ymitcloud.module.promotion.convert.combination.CombinationActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.combination.CombinationProductDO;
import com.ymitcloud.module.promotion.service.combination.CombinationActivityService;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;

/**
 * 用户 APP - 拼团活动
 */

@RestController
@RequestMapping("/promotion/combination-activity")
@Validated
public class AppCombinationActivityController {

    /**

     * {@link AppCombinationActivityRespVO} 缓存，通过它异步刷新
     * {@link #getCombinationActivityList0(Integer)} 所要的首页数据
     */
    private final LoadingCache<Integer, List<AppCombinationActivityRespVO>> combinationActivityListCache = buildAsyncReloadingCache(
            Duration.ofSeconds(10L), new CacheLoader<Integer, List<AppCombinationActivityRespVO>>() {


                @Override
                public List<AppCombinationActivityRespVO> load(Integer count) {
                    return getCombinationActivityList0(count);
                }

            });

    @Resource
    private CombinationActivityService activityService;

    @Resource
    private ProductSpuApi spuApi;


    /**
     * 获得拼团活动列表
     * 
     * @param count 需要展示的数量
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppCombinationActivityRespVO>> getCombinationActivityList(
            @RequestParam(name = "count", defaultValue = "6") Integer count) {
        return success(combinationActivityListCache.getUnchecked(count));
    }

    private List<AppCombinationActivityRespVO> getCombinationActivityList0(Integer count) {
        List<CombinationActivityDO> activityList = activityService.getCombinationActivityListByCount(count);
        if (CollUtil.isEmpty(activityList)) {
            return Collections.emptyList();
        }
        // 拼接返回

        List<CombinationProductDO> productList = activityService
                .getCombinationProductListByActivityIds(convertList(activityList, CombinationActivityDO::getId));

        List<ProductSpuRespDTO> spuList = spuApi.getSpuList(convertList(activityList, CombinationActivityDO::getSpuId));
        return CombinationActivityConvert.INSTANCE.convertAppList(activityList, productList, spuList);
    }


    /**
     * 获得拼团活动分页
     * 
     * @param pageParam
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppCombinationActivityRespVO>> getCombinationActivityPage(PageParam pageParam) {
        PageResult<CombinationActivityDO> pageResult = activityService.getCombinationActivityPage(pageParam);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }
        // 拼接返回
        List<CombinationProductDO> productList = activityService.getCombinationProductListByActivityIds(
                convertList(pageResult.getList(), CombinationActivityDO::getId));

        List<ProductSpuRespDTO> spuList = spuApi
                .getSpuList(convertList(pageResult.getList(), CombinationActivityDO::getSpuId));
        return success(CombinationActivityConvert.INSTANCE.convertAppPage(pageResult, productList, spuList));
    }

    /**
     * 获得拼团活动明细
     * 
     * @param id 活动编号
     * @return
     */
    @GetMapping("/get-detail")
    public CommonResult<AppCombinationActivityDetailRespVO> getCombinationActivityDetail(@RequestParam("id") Long id) {
        // 1. 获取活动
        CombinationActivityDO activity = activityService.getCombinationActivity(id);
        if (activity == null || ObjectUtil.equal(activity.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {

            return success(null);
        }

        // 2. 获取活动商品
        List<CombinationProductDO> products = activityService.getCombinationProductsByActivityId(activity.getId());
        return success(CombinationActivityConvert.INSTANCE.convert3(activity, products));
    }

}