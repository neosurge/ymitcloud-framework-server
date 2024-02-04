package com.ymitcloud.module.product.controller.admin.property.vo.value;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品属性值 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPropertyValueRespVO extends ProductPropertyValueBaseVO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
