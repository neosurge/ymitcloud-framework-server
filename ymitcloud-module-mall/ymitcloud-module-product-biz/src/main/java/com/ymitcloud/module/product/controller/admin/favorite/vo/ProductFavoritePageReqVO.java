package com.ymitcloud.module.product.controller.admin.favorite.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品收藏分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductFavoritePageReqVO extends PageParam {

    /**
     * 用户编号
     */

    private Long userId;

}
