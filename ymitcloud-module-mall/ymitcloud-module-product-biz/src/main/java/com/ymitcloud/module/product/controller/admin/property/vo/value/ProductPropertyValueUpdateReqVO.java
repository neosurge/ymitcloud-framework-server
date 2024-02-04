package com.ymitcloud.module.product.controller.admin.property.vo.value;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品属性值更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPropertyValueUpdateReqVO extends ProductPropertyValueBaseVO {

    /**
     * 主键
     */

    @NotNull(message = "主键不能为空")
    private Long id;

}
