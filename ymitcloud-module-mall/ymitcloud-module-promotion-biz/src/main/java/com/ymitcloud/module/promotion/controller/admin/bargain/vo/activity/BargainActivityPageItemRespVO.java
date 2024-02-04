package com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 砍价活动的分页项
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargainActivityPageItemRespVO extends BargainActivityBaseVO {


    /**
     * 活动编号
     */
    private Long id;

    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 商品主图
     */
    private String picUrl;

    /**
     * 活动状态
     */
    @NotNull(message = "活动状态不能为空")
    private Integer status;

    /**
     * 活动总库存
     */
    private Integer totalStock;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    // ========== 统计字段 ==========


    /**
     * 总砍价的用户数量
     */
    private Integer recordUserCount;

    /**
     * 成功砍价的用户数量
     */
    private Integer recordSuccessUserCount;

    /**
     * 帮助砍价的用户数量
     */

    private Integer helpUserCount;

}
