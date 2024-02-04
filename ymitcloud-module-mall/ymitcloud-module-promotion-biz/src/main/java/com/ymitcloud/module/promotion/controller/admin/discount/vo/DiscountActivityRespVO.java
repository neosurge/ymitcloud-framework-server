package com.ymitcloud.module.promotion.controller.admin.discount.vo;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 限时折扣活动 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscountActivityRespVO extends DiscountActivityBaseVO {


    /**
     * 活动编号
     */
    private Long id;

    /**
     * 活动状态
     */
    @NotNull(message = "活动状态不能为空")
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 商品 SPU 编号
     */
    private Long spuId;

    /**
     * 限时折扣商品
     */

    private List<DiscountActivityBaseVO.Product> products;

    // ========== 商品字段 ==========

    // TODO @zhangshuai：一个优惠活动，会关联多个商品，所以它不用返回 spuName 哈；
    // TODO 最终界面展示字段就：编号、活动名称、参与商品数、活动状态、开始时间、结束时间、操作

    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 商品主图
     */
    private String picUrl;
    /**
     * 商品市场价，单位：分
     */

    private Integer marketPrice;

}
