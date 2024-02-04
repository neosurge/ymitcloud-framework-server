package com.ymitcloud.module.crm.controller.admin.product.vo.product;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - CRM 产品创建/修改 Request VO
 */
@Data
public class CrmProductSaveReqVO {
    /**
     * 产品编号
     */
    private Long id;
    /**
     * 产品名称
     */
    @NotNull(message = "产品名称不能为空")
    private String name;
    /**
     * 产品编码
     */
    @NotNull(message = "产品编码不能为空")
    private String no;
    /**
     * 单位
     */
    private Integer unit;
    /**
     * 价格, 单位：分
     */
    @NotNull(message = "价格不能为空")
    private Long price;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 产品分类编号
     */
    @NotNull(message = "产品分类编号不能为空")
    private Long categoryId;
    /**
     * 产品描述
     */
    private String description;
    /**
     * 负责人的用户编号
     */

    @NotNull(message = "负责人的用户编号不能为空")
    private Long ownerUserId;

}
