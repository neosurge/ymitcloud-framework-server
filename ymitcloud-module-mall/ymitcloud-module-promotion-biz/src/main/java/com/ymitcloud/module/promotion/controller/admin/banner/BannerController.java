package com.ymitcloud.module.promotion.controller.admin.banner;


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
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerPageReqVO;
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerRespVO;
import com.ymitcloud.module.promotion.controller.admin.banner.vo.BannerUpdateReqVO;
import com.ymitcloud.module.promotion.convert.banner.BannerConvert;
import com.ymitcloud.module.promotion.dal.dataobject.banner.BannerDO;
import com.ymitcloud.module.promotion.service.banner.BannerService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - Banner 管理
 */

@RestController
@RequestMapping("/promotion/banner")
@Validated
public class BannerController {

    @Resource
    private BannerService bannerService;


    /**
     * 创建 Banner
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:banner:create')")
    public CommonResult<Long> createBanner(@Valid @RequestBody BannerCreateReqVO createReqVO) {
        return success(bannerService.createBanner(createReqVO));
    }


    /**
     * 更新 Banner
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:banner:update')")
    public CommonResult<Boolean> updateBanner(@Valid @RequestBody BannerUpdateReqVO updateReqVO) {
        bannerService.updateBanner(updateReqVO);
        return success(true);
    }


    /**
     * 删除 Banner
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:banner:delete')")
    public CommonResult<Boolean> deleteBanner(@RequestParam("id") Long id) {
        bannerService.deleteBanner(id);
        return success(true);
    }


    /**
     * 获得 Banner
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:banner:query')")
    public CommonResult<BannerRespVO> getBanner(@RequestParam("id") Long id) {
        BannerDO banner = bannerService.getBanner(id);
        return success(BannerConvert.INSTANCE.convert(banner));
    }


    /**
     * 获得 Banner 分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:banner:query')")
    public CommonResult<PageResult<BannerRespVO>> getBannerPage(@Valid BannerPageReqVO pageVO) {
        PageResult<BannerDO> pageResult = bannerService.getBannerPage(pageVO);
        return success(BannerConvert.INSTANCE.convertPage(pageResult));
    }

}
