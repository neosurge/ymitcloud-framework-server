package com.ymitcloud.module.product.controller.admin.comment.vo;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.product.controller.admin.sku.vo.ProductSkuBaseVO;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品评价 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCommentRespVO extends ProductCommentBaseVO {

    /**
     * 订单项编号
     */
    private Long id;
    /**
     * 是否匿名
     */
    private Boolean anonymous;
    /**
     * 交易订单编号
     */
    private Long orderId;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
     * 商家是否回复
     */
    private Boolean replyStatus;
    /**
     * 回复管理员编号
     */
    private Long replyUserId;
    /**
     * 商家回复内容
     */
    private String replyContent;
    /**
     * 商家回复时间
     */
    private LocalDateTime replyTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 评分星级 1-5 分
     */
    private Integer scores;
    /**
     * 商品 SPU 编号
     */
    @NotNull(message = "商品 SPU 编号不能为空")
    private Long spuId;
    /**
     * 商品 SPU 名称
     */
    @NotNull(message = "商品 SPU 名称不能为空")
    private String spuName;
    /**
     * 商品 SKU 图片地址
     */
    private String skuPicUrl;
    /**
     * 商品 SKU 规格值数组
     */

    private List<ProductSkuBaseVO.Property> skuProperties;

}
