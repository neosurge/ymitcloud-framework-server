package com.ymitcloud.module.promotion.controller.admin.discount.vo;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 限时折扣活动创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscountActivityCreateReqVO extends DiscountActivityBaseVO {

    /**
     * 商品列表
     */
    @NotEmpty(message = "商品列表不能为空")
    @Valid
    private List<Product> products;

}
