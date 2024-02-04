package com.ymitcloud.module.promotion.controller.admin.seckill;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;

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
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityDetailRespVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityRespVO;
import com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityUpdateReqVO;
import com.ymitcloud.module.promotion.convert.seckill.seckillactivity.SeckillActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.seckill.SeckillActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.seckill.SeckillProductDO;
import com.ymitcloud.module.promotion.service.seckill.SeckillActivityService;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 秒杀活动
 */

@RestController
@RequestMapping("/promotion/seckill-activity")
@Validated
public class SeckillActivityController {

    @Resource
    private SeckillActivityService seckillActivityService;
    @Resource
    private ProductSpuApi productSpuApi;


    /**
     * 创建秒杀活动
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-activity:create')")
    public CommonResult<Long> createSeckillActivity(@Valid @RequestBody SeckillActivityCreateReqVO createReqVO) {
        return success(seckillActivityService.createSeckillActivity(createReqVO));
    }


    /**
     * 更新秒杀活动
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-activity:update')")
    public CommonResult<Boolean> updateSeckillActivity(@Valid @RequestBody SeckillActivityUpdateReqVO updateReqVO) {
        seckillActivityService.updateSeckillActivity(updateReqVO);
        return success(true);
    }


    /**
     * 关闭秒杀活动
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/close")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-activity:close')")
    public CommonResult<Boolean> closeSeckillActivity(@RequestParam("id") Long id) {
        seckillActivityService.closeSeckillActivity(id);
        return success(true);
    }


    /**
     * 删除秒杀活动
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-activity:delete')")
    public CommonResult<Boolean> deleteSeckillActivity(@RequestParam("id") Long id) {
        seckillActivityService.deleteSeckillActivity(id);
        return success(true);
    }


    /**
     * 获得秒杀活动
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:seckill-activity:query')")
    public CommonResult<SeckillActivityDetailRespVO> getSeckillActivity(@RequestParam("id") Long id) {
        SeckillActivityDO activity = seckillActivityService.getSeckillActivity(id);
        List<SeckillProductDO> products = seckillActivityService.getSeckillProductListByActivityId(id);
        return success(SeckillActivityConvert.INSTANCE.convert(activity, products));
    }


    /**
     * 获得秒杀活动分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('promotion:seckill-activity:query')")
    public CommonResult<PageResult<SeckillActivityRespVO>> getSeckillActivityPage(
            @Valid SeckillActivityPageReqVO pageVO) {

        // 查询活动列表
        PageResult<SeckillActivityDO> pageResult = seckillActivityService.getSeckillActivityPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接数据

        List<SeckillProductDO> products = seckillActivityService
                .getSeckillProductListByActivityId(convertSet(pageResult.getList(), SeckillActivityDO::getId));
        List<ProductSpuRespDTO> spuList = productSpuApi
                .getSpuList(convertSet(pageResult.getList(), SeckillActivityDO::getSpuId));

        return success(SeckillActivityConvert.INSTANCE.convertPage(pageResult, products, spuList));
    }

}
