package com.ymitcloud.module.product.controller.admin.comment.vo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商品评价的商家回复 Request VO
 */
@Data
@ToString(callSuper = true)
public class ProductCommentReplyReqVO {
    /**
     * 评价编号
     */
    @NotNull(message = "评价编号不能为空")
    private Long id;
    /**
     * 商家回复内容
     */

    @NotEmpty(message = "商家回复内容不能为空")
    private String replyContent;

}
