package com.ymitcloud.module.product.controller.admin.brand.vo;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 品牌 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductBrandRespVO extends ProductBrandBaseVO {

    /**
     * 品牌编号
     */
    private Long id;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
