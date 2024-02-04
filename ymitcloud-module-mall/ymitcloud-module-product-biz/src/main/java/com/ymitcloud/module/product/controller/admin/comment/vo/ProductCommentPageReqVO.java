package com.ymitcloud.module.product.controller.admin.comment.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.product.enums.comment.ProductCommentScoresEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 商品评价分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCommentPageReqVO extends PageParam {

    /**
     * 评价人名称
     */
    private String userNickname;
    /**
     * 交易订单编号
     */
    private Long orderId;
    /**
     * 商品SPU编号
     */
    private Long spuId;
    /**
     * 商品SPU名称
     */
    private String spuName;
    /**
     * 评分星级 1-5 分
     */
    @InEnum(ProductCommentScoresEnum.class)
    private Integer scores;
    /**
     * 商家是否回复
     */
    private Boolean replyStatus;
    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
