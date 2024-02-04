package com.ymitcloud.module.promotion.controller.app.banner;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.promotion.controller.app.banner.vo.AppBannerRespVO;
import com.ymitcloud.module.promotion.convert.banner.BannerConvert;
import com.ymitcloud.module.promotion.dal.dataobject.banner.BannerDO;
import com.ymitcloud.module.promotion.service.banner.BannerService;


import jakarta.annotation.Resource;

/**
 * 用户 APP - 首页 Banner
 */
@RestController
@RequestMapping("/promotion/banner")

@Validated
public class AppBannerController {

    @Resource
    private BannerService bannerService;


    /**
     * 获得 banner 列表
     * 
     * @param position Banner position
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppBannerRespVO>> getBannerList(@RequestParam("position") Integer position) {
        List<BannerDO> bannerList = bannerService.getBannerListByPosition(position);
        return success(BannerConvert.INSTANCE.convertList01(bannerList));
    }


    /**
     * 增加 Banner 点击量
     * 
     * @param id Banner 编号
     * @return
     */
    @PutMapping("/add-browse-count")

    public CommonResult<Boolean> addBrowseCount(@RequestParam("id") Long id) {
        bannerService.addBannerBrowseCount(id);
        return success(true);
    }

}
