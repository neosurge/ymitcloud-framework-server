package com.ymit.module.system.controller.admin.permission.vo.role;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymit.framework.excel.core.annotations.DictFormat;
import com.ymit.framework.excel.core.convert.DictConvert;
import com.ymit.module.system.enums.DictTypeConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 管理后台 - 角色信息 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class RoleRespVO {
    /**
     * 角色编号
     */
    @ExcelProperty("角色序号")
    private Long id;
    /**
     * 角色名称
     */
    @ExcelProperty("角色名称")
    private String name;

    @NotBlank(message = "角色标志不能为空")
    @ExcelProperty("角色标志")
    private String code;
    /**
     * 显示顺序不能为空
     */
    @ExcelProperty("角色排序")
    private Integer sort;

    /**
     * 状态，参见 CommonStatusEnum 枚举类
     */
    @ExcelProperty(value = "角色状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 角色类型，参见 RoleTypeEnum 枚举类
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据范围，参见 DataScopeEnum 枚举类
     */
    @ExcelProperty("数据范围")
    private Integer dataScope;
    /**
     * 数据范围(指定部门数组)
     */
    private Set<Long> dataScopeDeptIds;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
