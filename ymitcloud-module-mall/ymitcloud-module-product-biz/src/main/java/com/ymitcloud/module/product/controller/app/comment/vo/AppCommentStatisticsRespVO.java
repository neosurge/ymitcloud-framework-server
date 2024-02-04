package com.ymitcloud.module.product.controller.app.comment.vo;


import lombok.Data;
import lombok.ToString;

/**
 * APP - 商品评价页评论分类数统计 Response VO
 */
@Data
@ToString(callSuper = true)
public class AppCommentStatisticsRespVO {
    /**
     * 好评数量
     */
    private Long goodCount;
    /**
     * 中评数量
     */
    private Long mediocreCount;
    /**
     * 差评数量
     */
    private Long negativeCount;
    /**
     * 总平均分
     */

    private Double scores;

}
