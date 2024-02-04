package com.ymitcloud.module.promotion.controller.app.combination.vo.activity;


import lombok.Data;

/**
 * 用户 App - 拼团活动
 */
@Data
public class AppCombinationActivityRespVO {

    /**
     * 拼团活动编号
     */
    private Long id;

    /**
     * 拼团活动名称
     */
    private String name;

    /**
     * 拼团人数
     */
    private Integer userSize;

    /**
     * 商品 SPU 编号
     */
    private Long spuId;

    /**
     * 商品图片
     */
    // 从 SPU 的 picUrl 读取
    private String picUrl;

    /**
     * 商品市场价，单位：分
     */
    // 从 SPU 的 marketPrice 读取
    private Integer marketPrice;

    /**
     * 拼团金额，单位：分
     */

    private Integer combinationPrice;

}
