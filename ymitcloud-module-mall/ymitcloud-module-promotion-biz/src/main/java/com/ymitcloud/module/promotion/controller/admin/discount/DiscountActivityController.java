package com.ymitcloud.module.promotion.controller.admin.discount;


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
import com.ymitcloud.module.promotion.controller.admin.discount.vo.DiscountActivityCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.discount.vo.DiscountActivityDetailRespVO;
import com.ymitcloud.module.promotion.controller.admin.discount.vo.DiscountActivityPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.discount.vo.DiscountActivityRespVO;
import com.ymitcloud.module.promotion.controller.admin.discount.vo.DiscountActivityUpdateReqVO;
import com.ymitcloud.module.promotion.convert.discount.DiscountActivityConvert;
import com.ymitcloud.module.promotion.dal.dataobject.discount.DiscountActivityDO;
import com.ymitcloud.module.promotion.dal.dataobject.discount.DiscountProductDO;
import com.ymitcloud.module.promotion.service.discount.DiscountActivityService;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 限时折扣活动
 */

@RestController
@RequestMapping("/promotion/discount-activity")
@Validated
public class DiscountActivityController {

    @Resource
    private DiscountActivityService discountActivityService;

    @Resource
    private ProductSpuApi productSpuApi;


    /**
     * 创建限时折扣活动
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:discount-activity:create')")
    public CommonResult<Long> createDiscountActivity(@Valid @RequestBody DiscountActivityCreateReqVO createReqVO) {
        return success(discountActivityService.createDiscountActivity(createReqVO));
    }


    /**
     * 更新限时折扣活动
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:discount-activity:update')")
    public CommonResult<Boolean> updateDiscountActivity(@Valid @RequestBody DiscountActivityUpdateReqVO updateReqVO) {
        discountActivityService.updateDiscountActivity(updateReqVO);
        return success(true);
    }


    /**
     * 关闭限时折扣活动
     * 
     * @param id 编号
     * @return
     */
    @PutMapping("/close")

    @PreAuthorize("@ss.hasPermission('promotion:discount-activity:close')")
    public CommonResult<Boolean> closeRewardActivity(@RequestParam("id") Long id) {
        discountActivityService.closeDiscountActivity(id);
        return success(true);
    }


    /**
     * 删除限时折扣活动
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:discount-activity:delete')")
    public CommonResult<Boolean> deleteDiscountActivity(@RequestParam("id") Long id) {
        discountActivityService.deleteDiscountActivity(id);
        return success(true);
    }


    /**
     * 获得限时折扣活动
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:discount-activity:query')")
    public CommonResult<DiscountActivityDetailRespVO> getDiscountActivity(@RequestParam("id") Long id) {
        DiscountActivityDO discountActivity = discountActivityService.getDiscountActivity(id);
        if (discountActivity == null) {
            return success(null);
        }
        // 拼接结果
        List<DiscountProductDO> discountProducts = discountActivityService.getDiscountProductsByActivityId(id);
        return success(DiscountActivityConvert.INSTANCE.convert(discountActivity, discountProducts));
    }


    /**
     * 获得限时折扣活动分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('promotion:discount-activity:query')")
    public CommonResult<PageResult<DiscountActivityRespVO>> getDiscountActivityPage(
            @Valid DiscountActivityPageReqVO pageVO) {
        PageResult<DiscountActivityDO> pageResult = discountActivityService.getDiscountActivityPage(pageVO);

        if (CollUtil.isEmpty(pageResult.getList())) { // TODO
                                                      // @zhangshuai：方法里的空行，目的是让代码分块，可以更清晰；所以上面这个空格可以不要，而下面判断之后的，空格，其实加下比较好；类似的还有
                                                      // spuList、以及后面的 convert
            return success(PageResult.empty(pageResult.getTotal()));
        }
        // 拼接数据
        List<DiscountProductDO> products = discountActivityService
                .getDiscountProductsByActivityId(convertSet(pageResult.getList(), DiscountActivityDO::getId));

        List<ProductSpuRespDTO> spuList = productSpuApi.getSpuList(convertSet(products, DiscountProductDO::getSpuId));


        return success(DiscountActivityConvert.INSTANCE.convertPage(pageResult, products, spuList));
    }

}
