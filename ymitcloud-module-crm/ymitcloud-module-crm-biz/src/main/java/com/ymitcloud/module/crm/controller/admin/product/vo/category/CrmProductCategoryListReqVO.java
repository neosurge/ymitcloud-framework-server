package com.ymitcloud.module.crm.controller.admin.product.vo.category;


import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 管理后台 - CRM 产品分类列表 Request VO
 */

@Data
public class CrmProductCategoryListReqVO {

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("父级 id")
    private Long parentId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
