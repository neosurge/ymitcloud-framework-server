package com.ymitcloud.module.promotion.controller.app.bargain;


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

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.api.spu.ProductSpuApi;
import com.ymitcloud.module.product.api.spu.dto.ProductSpuRespDTO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.activity.AppBargainActivityDetailRespVO;
import com.ymitcloud.module.promotion.controller.app.bargain.vo.activity.AppBargainActivityRespVO;
import com.ymitcloud.module.promotion.convert.bargain.BargainActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import com.ymitcloud.module.promotion.enums.bargain.BargainRecordStatusEnum;
import com.ymitcloud.module.promotion.service.bargain.BargainActivityService;
import com.ymitcloud.module.promotion.service.bargain.BargainRecordService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;

/**
 * 用户 App - 砍价活动
 */

@RestController
@RequestMapping("/promotion/bargain-activity")
@Validated
public class AppBargainActivityController {

    /**

     * {@link AppBargainActivityRespVO} 缓存，通过它异步刷新
     * {@link #getBargainActivityList0(Integer)} 所要的首页数据
     */
    private final LoadingCache<Integer, List<AppBargainActivityRespVO>> bargainActivityListCache = buildAsyncReloadingCache(
            Duration.ofSeconds(10L), new CacheLoader<Integer, List<AppBargainActivityRespVO>>() {


                @Override
                public List<AppBargainActivityRespVO> load(Integer count) {
                    return getBargainActivityList0(count);
                }

            });

    @Resource
    private BargainActivityService bargainActivityService;
    @Resource
    private BargainRecordService bargainRecordService;

    @Resource
    private ProductSpuApi spuApi;


    /**
     * 获得砍价活动列表
     * 
     * @param count 需要展示的数量
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppBargainActivityRespVO>> getBargainActivityList(
            @RequestParam(name = "count", defaultValue = "6") Integer count) {
        return success(bargainActivityListCache.getUnchecked(count));
    }


    private List<AppBargainActivityRespVO> getBargainActivityList0(Integer count) {

        List<BargainActivityDO> list = bargainActivityService.getBargainActivityListByCount(count);
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        // 拼接数据
        List<ProductSpuRespDTO> spuList = spuApi.getSpuList(convertList(list, BargainActivityDO::getSpuId));
        return BargainActivityConvert.INSTANCE.convertAppList(list, spuList);
    }


    /**
     * 获得砍价活动分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppBargainActivityRespVO>> getBargainActivityPage(PageParam pageReqVO) {
        PageResult<BargainActivityDO> result = bargainActivityService.getBargainActivityPage(pageReqVO);
        if (CollUtil.isEmpty(result.getList())) {
            return success(PageResult.empty(result.getTotal()));
        }
        // 拼接数据
        List<ProductSpuRespDTO> spuList = spuApi.getSpuList(convertList(result.getList(), BargainActivityDO::getSpuId));
        return success(BargainActivityConvert.INSTANCE.convertAppPage(result, spuList));
    }


    /**
     * 获得砍价活动详情
     * 
     * @param id 活动编号
     * @return
     */
    @GetMapping("/get-detail")

    public CommonResult<AppBargainActivityDetailRespVO> getBargainActivityDetail(@RequestParam("id") Long id) {
        BargainActivityDO activity = bargainActivityService.getBargainActivity(id);
        if (activity == null) {
            return success(null);
        }
        // 拼接数据

        Integer successUserCount = bargainRecordService.getBargainRecordUserCount(id,
                BargainRecordStatusEnum.SUCCESS.getStatus());

        ProductSpuRespDTO spu = spuApi.getSpu(activity.getSpuId());
        return success(BargainActivityConvert.INSTANCE.convert(activity, successUserCount, spu));
    }

}
