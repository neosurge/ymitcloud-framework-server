package com.ymitcloud.module.product.controller.admin.favorite.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品收藏 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductFavoriteBaseVO {
    /**
     * 用户编号
     */

    @NotNull(message = "用户编号不能为空")
    private Long userId;

}
