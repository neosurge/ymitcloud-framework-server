package com.ymitcloud.module.product.controller.admin.spu.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.product.enums.spu.ProductSpuStatusEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 商品 SPU Status 更新 Request VO
 */
@Data
public class ProductSpuUpdateStatusReqVO {
    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空")
    private Long id;
    /**
     * 商品状态
     */

    @NotNull(message = "商品状态不能为空")
    @InEnum(ProductSpuStatusEnum.class)
    private Integer status;

}
