package com.ymitcloud.module.promotion.controller.admin.coupon;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.collection.MapUtils;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon.CouponPageItemRespVO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon.CouponPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon.CouponSendReqVO;
import com.ymitcloud.module.promotion.convert.coupon.CouponConvert;
import com.ymitcloud.module.promotion.dal.dataobject.coupon.CouponDO;
import com.ymitcloud.module.promotion.service.coupon.CouponService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 优惠劵
 */

@RestController
@RequestMapping("/promotion/coupon")
@Validated
public class CouponController {

    @Resource
    private CouponService couponService;
    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 回收优惠劵
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:coupon:delete')")
    public CommonResult<Boolean> deleteCoupon(@RequestParam("id") Long id) {
        couponService.deleteCoupon(id);
        return success(true);
    }


    /**
     * 获得优惠劵分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:coupon:query')")
    public CommonResult<PageResult<CouponPageItemRespVO>> getCouponPage(@Valid CouponPageReqVO pageVO) {
        PageResult<CouponDO> pageResult = couponService.getCouponPage(pageVO);
        PageResult<CouponPageItemRespVO> pageResulVO = CouponConvert.INSTANCE.convertPage(pageResult);
        if (CollUtil.isEmpty(pageResulVO.getList())) {
            return success(pageResulVO);
        }

        // 读取用户信息，进行拼接

        Map<Long, MemberUserRespDTO> userMap = memberUserApi
                .getUserMap(convertSet(pageResult.getList(), CouponDO::getUserId));

        pageResulVO.getList().forEach(itemRespVO -> MapUtils.findAndThen(userMap, itemRespVO.getUserId(),
                userRespDTO -> itemRespVO.setNickname(userRespDTO.getNickname())));
        return success(pageResulVO);
    }


    /**
     * 发送优惠劵
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/send")

    @PreAuthorize("@ss.hasPermission('promotion:coupon:send')")
    public CommonResult<Boolean> sendCoupon(@Valid @RequestBody CouponSendReqVO reqVO) {
        couponService.takeCouponByAdmin(reqVO.getTemplateId(), reqVO.getUserIds());
        return success(true);
    }

}
