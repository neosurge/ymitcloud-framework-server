package com.ymitcloud.module.crm.controller.admin.contact.vo;


import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - CRM 联系人 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ExcelIgnoreUnannotated
public class CrmContactRespVO extends CrmContactBaseVO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", order = 8)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间", order = 8)
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人名字
     */
    @ExcelProperty(value = "创建人", order = 8)
    private String creatorName;

    /**
     * 客户名字
     */
    @ExcelProperty(value = "", order = 2)
    private String customerName;

    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人", order = 7)
    private String ownerUserName;

    /**
     * 直属上级名
     */
    @ExcelProperty(value = "直属上级", order = 4)
    private String parentName;
    /**
     * 地区名
     */

    @ExcelProperty(value = "地区", order = 5)
    private String areaName;

}
