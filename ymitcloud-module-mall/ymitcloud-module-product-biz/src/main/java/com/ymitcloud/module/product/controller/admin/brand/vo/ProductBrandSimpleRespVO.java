package com.ymitcloud.module.product.controller.admin.brand.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 管理后台 - 品牌精简信息 Response VO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBrandSimpleRespVO {


    /**
     * 品牌编号
     */
    private Long id;
    /**
     * 品牌名称
     */

    private String name;

}
