package com.ymitcloud.module.promotion.controller.admin.coupon;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;


import java.util.Collection;
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
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.template.CouponTemplateCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.template.CouponTemplatePageReqVO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.template.CouponTemplateRespVO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.template.CouponTemplateUpdateReqVO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.template.CouponTemplateUpdateStatusReqVO;
import com.ymitcloud.module.promotion.convert.coupon.CouponTemplateConvert;
import com.ymitcloud.module.promotion.dal.dataobject.coupon.CouponTemplateDO;
import com.ymitcloud.module.promotion.service.coupon.CouponTemplateService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 优惠劵模板
 */

@RestController
@RequestMapping("/promotion/coupon-template")
@Validated
public class CouponTemplateController {

    @Resource
    private CouponTemplateService couponTemplateService;


    /**
     * 创建优惠劵模板
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:create')")
    public CommonResult<Long> createCouponTemplate(@Valid @RequestBody CouponTemplateCreateReqVO createReqVO) {
        return success(couponTemplateService.createCouponTemplate(createReqVO));
    }


    /**
     * 更新优惠劵模板
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:update')")
    public CommonResult<Boolean> updateCouponTemplate(@Valid @RequestBody CouponTemplateUpdateReqVO updateReqVO) {
        couponTemplateService.updateCouponTemplate(updateReqVO);
        return success(true);
    }


    /**
     * 更新优惠劵模板状态
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-status")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:update')")
    public CommonResult<Boolean> updateCouponTemplateStatus(@Valid @RequestBody CouponTemplateUpdateStatusReqVO reqVO) {
        couponTemplateService.updateCouponTemplateStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }


    /**
     * 删除优惠劵模板
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:delete')")
    public CommonResult<Boolean> deleteCouponTemplate(@RequestParam("id") Long id) {
        couponTemplateService.deleteCouponTemplate(id);
        return success(true);
    }


    /**
     * 获得优惠劵模板
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:query')")
    public CommonResult<CouponTemplateRespVO> getCouponTemplate(@RequestParam("id") Long id) {
        CouponTemplateDO couponTemplate = couponTemplateService.getCouponTemplate(id);
        return success(CouponTemplateConvert.INSTANCE.convert(couponTemplate));
    }


    /**
     * 获得优惠劵模板分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:query')")
    public CommonResult<PageResult<CouponTemplateRespVO>> getCouponTemplatePage(@Valid CouponTemplatePageReqVO pageVO) {
        PageResult<CouponTemplateDO> pageResult = couponTemplateService.getCouponTemplatePage(pageVO);
        return success(CouponTemplateConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获得优惠劵模板列表
     * 
     * @param ids 编号列表
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('promotion:coupon-template:query')")
    public CommonResult<List<CouponTemplateRespVO>> getCouponTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<CouponTemplateDO> list = couponTemplateService.getCouponTemplateList(ids);
        return success(CouponTemplateConvert.INSTANCE.convertList(list));
    }

}
