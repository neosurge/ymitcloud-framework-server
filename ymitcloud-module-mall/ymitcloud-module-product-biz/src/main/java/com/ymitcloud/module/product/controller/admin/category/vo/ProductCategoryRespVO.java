package com.ymitcloud.module.product.controller.admin.category.vo;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商品分类 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCategoryRespVO extends ProductCategoryBaseVO {

    /**
     * 分类编号
     */
    private Long id;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
