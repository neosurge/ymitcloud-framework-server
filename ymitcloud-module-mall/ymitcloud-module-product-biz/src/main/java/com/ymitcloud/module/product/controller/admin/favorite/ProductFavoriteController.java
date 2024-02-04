package com.ymitcloud.module.product.controller.admin.favorite;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.controller.admin.favorite.vo.ProductFavoritePageReqVO;
import com.ymitcloud.module.product.controller.admin.favorite.vo.ProductFavoriteRespVO;
import com.ymitcloud.module.product.convert.favorite.ProductFavoriteConvert;
import com.ymitcloud.module.product.dal.dataobject.favorite.ProductFavoriteDO;
import com.ymitcloud.module.product.dal.dataobject.spu.ProductSpuDO;
import com.ymitcloud.module.product.service.favorite.ProductFavoriteService;
import com.ymitcloud.module.product.service.spu.ProductSpuService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商品收藏
 */

@RestController
@RequestMapping("/product/favorite")
@Validated
public class ProductFavoriteController {

    @Resource
    private ProductFavoriteService productFavoriteService;

    @Resource
    private ProductSpuService productSpuService;


    /**
     * 获得商品收藏分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('product:favorite:query')")
    public CommonResult<PageResult<ProductFavoriteRespVO>> getFavoritePage(@Valid ProductFavoritePageReqVO pageVO) {
        PageResult<ProductFavoriteDO> pageResult = productFavoriteService.getFavoritePage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }
        // 拼接数据

        List<ProductSpuDO> spuList = productSpuService
                .getSpuList(convertSet(pageResult.getList(), ProductFavoriteDO::getSpuId));

        return success(ProductFavoriteConvert.INSTANCE.convertPage(pageResult, spuList));
    }

}
