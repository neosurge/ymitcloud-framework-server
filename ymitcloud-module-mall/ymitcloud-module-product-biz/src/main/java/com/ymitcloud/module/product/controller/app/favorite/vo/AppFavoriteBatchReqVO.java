package com.ymitcloud.module.product.controller.app.favorite.vo;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 用户 APP - 商品收藏的批量 Request VO
 */
@Data
public class AppFavoriteBatchReqVO {
    /**
     * 商品 SPU 编号数组
     */

    @NotEmpty(message = "商品 SPU 编号数组不能为空")
    private List<Long> spuIds;

}
