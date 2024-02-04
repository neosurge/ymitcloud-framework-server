package com.ymitcloud.module.crm.controller.admin.product.vo.category;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - CRM 产品分类创建/更新 Request VO
 */
@Data
public class CrmProductCategoryCreateReqVO {
    /**
     * 分类编号
     */
    private Long id;
    /**
     * 分类名称
     */
    @NotNull(message = "分类名称不能为空")
    private String name;
    /**
     * 父级编号
     */

    @NotNull(message = "父级编号不能为空")
    private Long parentId;

}
