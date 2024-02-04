package com.ymitcloud.module.crm.controller.admin.business.vo.status;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;


import lombok.Data;

/**
 * 管理后台 - 商机状态 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class CrmBusinessStatusRespVO {
    /**
     * 主键
     */
    @ExcelProperty("主键")
    private Long id;
    /**
     * 状态类型编号
     */
    @ExcelProperty("状态类型编号")
    private Long typeId;
    /**
     * 状态名
     */
    @ExcelProperty("状态名")
    private String name;
    /**
     * 赢单率
     */
    @ExcelProperty("赢单率")
    private String percent;
    /**
     * 排序
     */

    @ExcelProperty("排序")
    private Integer sort;

}
