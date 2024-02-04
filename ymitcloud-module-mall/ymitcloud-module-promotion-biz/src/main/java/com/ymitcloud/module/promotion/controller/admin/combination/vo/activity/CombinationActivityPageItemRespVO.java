package com.ymitcloud.module.promotion.controller.admin.combination.vo.activity;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.combination.vo.product.CombinationProductRespVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 拼团活动的分页项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationActivityPageItemRespVO extends CombinationActivityBaseVO {


    /**
     * 活动编号
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 拼团商品
     */

    private List<CombinationProductRespVO> products;

    // ========== 商品字段 ==========


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

    // ========== 统计字段 ==========


    /**
     * 开团组数
     */
    private Integer groupCount;

    /**
     * 成团组数
     */
    private Integer groupSuccessCount;

    /**
     * 购买次数
     */

    private Integer recordCount;

}
