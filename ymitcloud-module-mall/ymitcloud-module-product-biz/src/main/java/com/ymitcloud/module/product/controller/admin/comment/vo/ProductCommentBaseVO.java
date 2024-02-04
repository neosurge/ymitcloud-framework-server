package com.ymitcloud.module.product.controller.admin.comment.vo;


import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductCommentBaseVO {
    /**
     * 评价人
     */
    private Long userId;
    /**
     * 评价订单项
     */
    private Long orderItemId;
    /**
     * 评价人名称
     */
    @NotNull(message = "评价人名称不能为空")
    private String userNickname;
    /**
     * 评价人头像
     */
    @NotNull(message = "评价人头像不能为空")
    private String userAvatar;
    /**
     * 商品 SKU 编号
     */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;
    /**
     * 描述星级 1-5 分
     */
    @NotNull(message = "描述星级不能为空")
    private Integer descriptionScores;
    /**
     * 服务星级 1-5 分
     */
    @NotNull(message = "服务星级分不能为空")
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
