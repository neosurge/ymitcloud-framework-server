package com.ymitcloud.module.promotion.controller.admin.discount.vo;


import java.util.List;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  管理后台 - 限时折扣活动更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscountActivityUpdateReqVO extends DiscountActivityBaseVO {


    /** 
     * 活动编号
     */

    @NotNull(message = "活动编号不能为空")
    private Long id;

    /**
     * 商品列表
     */
    @NotEmpty(message = "商品列表不能为空")
    @Valid
    private List<DiscountActivityCreateReqVO.Product> products;

}
