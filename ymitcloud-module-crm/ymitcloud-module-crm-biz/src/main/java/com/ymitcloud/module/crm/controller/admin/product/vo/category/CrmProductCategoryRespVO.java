package com.ymitcloud.module.crm.controller.admin.product.vo.category;


import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - CRM 产品分类 Response VO
 */
@Data
public class CrmProductCategoryRespVO {
    /**
     * 分类编号
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父级编号
     */
    private Long parentId;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
