package com.ymitcloud.module.promotion.controller.app.coupon.vo.coupon;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - 优惠劵的匹配 Request VO
 */
@Data
public class AppCouponMatchReqVO {

    /**
     * 商品金额
     */
    @NotNull(message = "商品金额不能为空")
    private Integer price;

    /**
     * 商品 SPU 编号的数组
     */
    @NotEmpty(message = "商品 SPU 编号不能为空")
    private List<Long> spuIds;

    /**
     * 商品 SKU 编号的数组
     */
    @NotEmpty(message = "商品 SKU 编号不能为空")
    private List<Long> skuIds;

    /**
     * 分类编号的数组
     */

    @NotEmpty(message = "分类编号不能为空")
    private List<Long> categoryIds;

}
