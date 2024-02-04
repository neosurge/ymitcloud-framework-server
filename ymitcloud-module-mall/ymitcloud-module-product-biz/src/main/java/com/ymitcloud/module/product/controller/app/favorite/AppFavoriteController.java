package com.ymitcloud.module.product.controller.app.favorite;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertList;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.product.controller.app.favorite.vo.AppFavoriteBatchReqVO;
import com.ymitcloud.module.product.controller.app.favorite.vo.AppFavoritePageReqVO;
import com.ymitcloud.module.product.controller.app.favorite.vo.AppFavoriteReqVO;
import com.ymitcloud.module.product.controller.app.favorite.vo.AppFavoriteRespVO;
import com.ymitcloud.module.product.convert.favorite.ProductFavoriteConvert;
import com.ymitcloud.module.product.dal.dataobject.favorite.ProductFavoriteDO;
import com.ymitcloud.module.product.dal.dataobject.spu.ProductSpuDO;
import com.ymitcloud.module.product.service.favorite.ProductFavoriteService;
import com.ymitcloud.module.product.service.spu.ProductSpuService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 用户 APP - 商品收藏
 */

@RestController
@RequestMapping("/product/favorite")
public class AppFavoriteController {

    @Resource
    private ProductFavoriteService productFavoriteService;
    @Resource
    private ProductSpuService productSpuService;


    /**
     * 添加商品收藏
     * 
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/create")

    @PreAuthenticated
    public CommonResult<Long> createFavorite(@RequestBody @Valid AppFavoriteReqVO reqVO) {
        return success(productFavoriteService.createFavorite(getLoginUserId(), reqVO.getSpuId()));
    }


    /**
     * 添加多个商品收藏
     * 
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/create-list")

    @PreAuthenticated
    public CommonResult<Boolean> createFavoriteList(@RequestBody @Valid AppFavoriteBatchReqVO reqVO) {
        // todo @jason：待实现；如果有已经收藏的，不用报错，忽略即可；
        return success(true);
    }


    /**
     * 取消单个商品收藏
     * 
     * @param reqVO
     * @return
     */
    @DeleteMapping(value = "/delete")

    @PreAuthenticated
    public CommonResult<Boolean> deleteFavorite(@RequestBody @Valid AppFavoriteReqVO reqVO) {
        productFavoriteService.deleteFavorite(getLoginUserId(), reqVO.getSpuId());
        return success(Boolean.TRUE);
    }


    /**
     * 取消多个商品收藏
     * 
     * @param reqVO
     * @return
     */
    @DeleteMapping(value = "/delete-list")

    @PreAuthenticated
    public CommonResult<Boolean> deleteFavoriteList(@RequestBody @Valid AppFavoriteBatchReqVO reqVO) {
        // todo @jason：待实现
//        productFavoriteService.deleteFavorite(getLoginUserId(), reqVO.getSpuId());
        return success(Boolean.TRUE);
    }


    /**
     * 获得商品收藏分页
     * 
     * @param reqVO
     * @return
     */
    @GetMapping(value = "/page")

    @PreAuthenticated
    public CommonResult<PageResult<AppFavoriteRespVO>> getFavoritePage(AppFavoritePageReqVO reqVO) {
        PageResult<ProductFavoriteDO> favoritePage = productFavoriteService.getFavoritePage(getLoginUserId(), reqVO);
        if (CollUtil.isEmpty(favoritePage.getList())) {
            return success(PageResult.empty());
        }

        // 得到商品 spu 信息
        List<ProductFavoriteDO> favorites = favoritePage.getList();
        List<Long> spuIds = convertList(favorites, ProductFavoriteDO::getSpuId);
        List<ProductSpuDO> spus = productSpuService.getSpuList(spuIds);

        // 转换 VO 结果
        PageResult<AppFavoriteRespVO> pageResult = new PageResult<>(favoritePage.getTotal());
        pageResult.setList(ProductFavoriteConvert.INSTANCE.convertList(favorites, spus));
        return success(pageResult);
    }


    /**
     * 检查是否收藏过商品
     * 
     * @param reqVO
     * @return
     */
    @GetMapping(value = "/exits")

    @PreAuthenticated
    public CommonResult<Boolean> isFavoriteExists(AppFavoriteReqVO reqVO) {
        ProductFavoriteDO favorite = productFavoriteService.getFavorite(getLoginUserId(), reqVO.getSpuId());
        return success(favorite != null);
    }


    /**
     * 获得商品收藏数量
     * 
     * @return
     */
    @GetMapping(value = "/get-count")

    @PreAuthenticated
    public CommonResult<Long> getFavoriteCount() {
        return success(productFavoriteService.getFavoriteCount(getLoginUserId()));
    }

}
