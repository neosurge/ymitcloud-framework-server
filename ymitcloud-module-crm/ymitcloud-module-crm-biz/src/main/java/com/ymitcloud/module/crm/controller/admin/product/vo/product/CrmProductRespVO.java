package com.ymitcloud.module.crm.controller.admin.product.vo.product;


import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;
import com.ymitcloud.module.crm.enums.DictTypeConstants;

import lombok.Data;

/**
 * 管理后台 - CRM 产品 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class CrmProductRespVO {
    /**
     * 产品编号
     */
    @ExcelProperty("产品编号")
    private Long id;
    /**
     * 产品名称
     */
    @ExcelProperty("产品名称")
    private String name;
    /**
     * 产品编码
     */
    @ExcelProperty("产品编码")
    private String no;
    /**
     * 单位
     */
    @ExcelProperty(value = "单位", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.CRM_PRODUCT_UNIT)
    private Integer unit;
    /**
     * 价格, 单位：分
     */
    @ExcelProperty("价格，单位：分")
    private Long price;
    /**
     * 状态
     */
    @ExcelProperty(value = "单位", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.CRM_PRODUCT_STATUS)
    private Integer status;
    /**
     * 产品分类编号
     */
    private Long categoryId;
    /**
     * 产品分类名字
     */
    @ExcelProperty("产品分类")
    private String categoryName;
    /**
     * 产品描述
     */
    @ExcelProperty("产品描述")
    private String description;
    /**
     * 负责人的用户编号
     */
    private Long ownerUserId;
    /**
     * 负责人的用户昵称
     */
    @ExcelProperty("负责人")
    private String ownerUserName;
    /**
     * 创建人编号
     */
    private String creator;
    /**
     * 创建人名字
     */
    @ExcelProperty("创建人")
    private String creatorName;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
