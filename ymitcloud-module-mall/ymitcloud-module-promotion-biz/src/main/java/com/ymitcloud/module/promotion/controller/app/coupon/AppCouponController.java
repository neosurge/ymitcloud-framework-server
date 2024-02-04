package com.ymitcloud.module.promotion.controller.app.coupon;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collections;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon.AppCouponMatchReqVO;
import com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon.AppCouponMatchRespVO;
import com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon.AppCouponPageReqVO;
import com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon.AppCouponRespVO;
import com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon.AppCouponTakeReqVO;

import com.ymitcloud.module.promotion.convert.coupon.CouponConvert;
import com.ymitcloud.module.promotion.dal.dataobject.coupon.CouponDO;
import com.ymitcloud.module.promotion.dal.dataobject.coupon.CouponTemplateDO;
import com.ymitcloud.module.promotion.enums.coupon.CouponTakeTypeEnum;
import com.ymitcloud.module.promotion.service.coupon.CouponService;
import com.ymitcloud.module.promotion.service.coupon.CouponTemplateService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 用户 App - 优惠劵
 */

@RestController
@RequestMapping("/promotion/coupon")
@Validated
public class AppCouponController {

    @Resource
    private CouponService couponService;
    @Resource
    private CouponTemplateService couponTemplateService;


    /**
     * 领取优惠劵
     * 
     * @param reqVO 优惠券模板编号
     * @return
     */
    @PostMapping("/take")

    @PreAuthenticated
    public CommonResult<Boolean> takeCoupon(@Valid @RequestBody AppCouponTakeReqVO reqVO) {
        // 1. 领取优惠劵
        Long userId = getLoginUserId();
        couponService.takeCoupon(reqVO.getTemplateId(), CollUtil.newHashSet(userId), CouponTakeTypeEnum.USER);

        // 2. 检查是否可以继续领取
        CouponTemplateDO couponTemplate = couponTemplateService.getCouponTemplate(reqVO.getTemplateId());
        boolean canTakeAgain = true;
        if (couponTemplate.getTakeLimitCount() != null && couponTemplate.getTakeLimitCount() > 0) {
            Integer takeCount = couponService.getTakeCount(reqVO.getTemplateId(), userId);
            canTakeAgain = takeCount < couponTemplate.getTakeLimitCount();
        }
        return success(canTakeAgain);
    }


    /**
     * 获得匹配指定商品的优惠劵列表
     * 
     * @param matchReqVO
     * @return
     */
    @GetMapping("/match-list")
    public CommonResult<List<AppCouponMatchRespVO>> getMatchCouponList(AppCouponMatchReqVO matchReqVO) {
        // todo: 优化：优惠金额倒序
        return success(
                CouponConvert.INSTANCE.convertList(couponService.getMatchCouponList(getLoginUserId(), matchReqVO)));
    }

    /**
     * 我的优惠劵列表
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthenticated
    public CommonResult<PageResult<AppCouponRespVO>> getCouponPage(AppCouponPageReqVO pageReqVO) {
        PageResult<CouponDO> pageResult = couponService
                .getCouponPage(CouponConvert.INSTANCE.convert(pageReqVO, Collections.singleton(getLoginUserId())));
        return success(CouponConvert.INSTANCE.convertAppPage(pageResult));
    }

    /**
     * 获得未使用的优惠劵数量
     * 
     * @return
     */
    @GetMapping(value = "/get-unused-count")

    @PreAuthenticated
    public CommonResult<Long> getUnusedCouponCount() {
        return success(couponService.getUnusedCouponCount(getLoginUserId()));
    }

}
