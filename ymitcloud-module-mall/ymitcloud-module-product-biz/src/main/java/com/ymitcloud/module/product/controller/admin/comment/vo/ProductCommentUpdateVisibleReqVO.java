package com.ymitcloud.module.product.controller.admin.comment.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * 管理后台 - 商品评价可见修改 Request VO
 */
@Data
@ToString(callSuper = true)
public class ProductCommentUpdateVisibleReqVO {
    /**
     * 评价编号
     */
    @NotNull(message = "评价编号不能为空")
    private Long id;
    /**
     * 是否可见
     */

    @NotNull(message = "是否可见不能为空")
    private Boolean visible;

}
