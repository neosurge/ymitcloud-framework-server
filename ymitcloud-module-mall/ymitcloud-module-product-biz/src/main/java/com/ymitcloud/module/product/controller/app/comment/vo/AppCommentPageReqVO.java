package com.ymitcloud.module.product.controller.app.comment.vo;

import com.ymitcloud.framework.common.pojo.PageParam;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 用户 App - 商品评价分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCommentPageReqVO extends PageParam {

    /**
     * 好评
     */
    public static final Integer GOOD_COMMENT = 1;
    /**
     * 中评
     */
    public static final Integer MEDIOCRE_COMMENT = 2;
    /**
     * 差评
     */
    public static final Integer NEGATIVE_COMMENT = 3;

    /**
     * 商品SPU编号
     */
    @NotNull(message = "商品SPU编号不能为空")
    private Long spuId;
    /**
     * app 评论页 tab 类型 (0 全部、1 好评、2 中评、3 差评)
     */

    @NotNull(message = "商品SPU编号不能为空")
    private Integer type;

}
