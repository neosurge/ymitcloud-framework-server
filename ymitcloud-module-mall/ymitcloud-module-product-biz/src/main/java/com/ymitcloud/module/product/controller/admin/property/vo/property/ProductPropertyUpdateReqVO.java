package com.ymitcloud.module.product.controller.admin.property.vo.property;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 属性项更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPropertyUpdateReqVO extends ProductPropertyBaseVO {

    /**
     * 主键
     */

    @NotNull(message = "主键不能为空")
    private Long id;

}
