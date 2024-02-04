package com.ymitcloud.module.promotion.controller.admin.discount.vo;


import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 
 * 管理后台 - 限时折扣活动的详细 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscountActivityDetailRespVO extends DiscountActivityRespVO {

    /**
     * 商品列表
     */
    private List<Product> products;

}
