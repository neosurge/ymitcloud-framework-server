package com.ymitcloud.module.product.controller.admin.favorite.vo;

import com.ymitcloud.module.product.controller.admin.spu.vo.ProductSpuRespVO;


import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商品收藏 Response VO
 */
@Data
@ToString(callSuper = true)
public class ProductFavoriteRespVO extends ProductSpuRespVO {

    /**
     * 用户编号
     */
    private Long userId;
    /**
     * SPU编号
     */

    private Long spuId;

}
