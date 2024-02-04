package com.ymitcloud.module.crm.controller.admin.business.vo.type;


import java.time.LocalDateTime;
import java.util.List;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.module.crm.dal.dataobject.business.CrmBusinessStatusDO;

import lombok.Data;

/**
 * 管理后台 - 商机状态类型 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class CrmBusinessStatusTypeRespVO {
    /**
     * 主键
     */
    @ExcelProperty("主键")
    private Long id;
    /**
     * 状态类型名
     */
    @ExcelProperty("状态类型名")
    private String name;
    /**
     * 使用的部门编号
     */
    @ExcelProperty("使用的部门编号")
    private List<Long> deptIds;
    /**
     * 使用的部门名称
     */
    @ExcelProperty("使用的部门名称")
    private List<String> deptNames;
    /**
     * 创建人
     */
    @ExcelProperty("创建人")
    private String creator;
    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 状态集合
     */
    // TODO @ljlleo 字段后缀改成 statuses，保持和 deptIds 风格一致；CrmBusinessStatusDO 改成 VO
    // 哈；一般不使用 do 直接返回

    private List<CrmBusinessStatusDO> statusList;

}
