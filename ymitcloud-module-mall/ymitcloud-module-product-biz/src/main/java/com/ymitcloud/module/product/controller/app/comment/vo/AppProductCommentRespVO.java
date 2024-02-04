package com.ymitcloud.module.product.controller.app.comment.vo;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.product.controller.app.property.vo.value.AppProductPropertyValueDetailRespVO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/**
 * 用户 App - 商品评价详情 Response VO
 */
@Data
@ToString(callSuper = true)
public class AppProductCommentRespVO {
    /**
     * 评价人的用户编号
     */
    private Long userId;
    /**
     * 评价人名称
     */
    private String userNickname;
    /**
     * 评价人头像
     */
    private String userAvatar;
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
     * 交易订单项编号
     */
    private Long orderItemId;
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
     * 追加评价内容
     */
    private String additionalContent;
    /**
     * 追评评价图片地址数组，以逗号分隔最多上传 9 张
     */
    private List<String> additionalPicUrls;
    /**
     * 追加评价时间
     */
    private LocalDateTime additionalTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
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
     * 商品 SKU 编号
     */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;
    /**
     * 商品 SKU 属性
     */
    private List<AppProductPropertyValueDetailRespVO> skuProperties;
    /**
     * 评分星级 1-5 分
     */
    @NotNull(message = "评分星级 1-5 分不能为空")
    private Integer scores;
    /**
     * 描述星级 1-5 分
     */
    @NotNull(message = "描述星级 1-5 分不能为空")
    private Integer descriptionScores;
    /**
     * 服务星级 1-5 分
     */
    @NotNull(message = "服务星级 1-5 分不能为空")
    private Integer benefitScores;
    /**
     * 评论内容
     */
    @NotNull(message = "评论内容不能为空")
    private String content;
    /**
     * 评论图片地址数组，以逗号分隔最多上传 9 张
     */

    @Size(max = 9, message = "评论图片地址数组长度不能超过 9 张")
    private List<String> picUrls;

}
